from fastapi import FastAPI, Request
from fastapi.responses import JSONResponse
import paramiko
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

# Configuración de CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_methods=["*"],
    allow_headers=["*"],
)

# Variable para almacenar el proceso de Ryu
ryu_process = None

@app.post("/start-ryu")
async def start_ryu(request: Request):
    global ryu_process
    try:
        data = await request.json()  # Obtener los datos JSON del cuerpo de la solicitud
        app_name = data.get('app_name')
        my_string = data.get('my_string', "String no recibido")  # Obtener el string
        
        print(f"Recibido el string: {app_name}")  # Imprimir el string recibido
        # Conectar al servidor remoto via SSH
        ssh = paramiko.SSHClient()
        ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        ssh.connect(hostname="192.168.101.72", username="ryu", password="ryu")

        # Ejecutar el comando ryu-manager con el app 'simple_switch.py'
        #command = "ryu-manager /usr/lib/python3/dist-packages/ryu/app/simple_switch.py"
        command = f"ryu-manager /usr/lib/python3/dist-packages/ryu/app/{app_name}"
        ryu_process = ssh.exec_command(command, get_pty=True)  # Ejecuta el comando y obtiene el canal

        # Acceder al canal de stdin y stdout
        stdin, stdout, stderr = ryu_process

        return JSONResponse({"message": "Aplicación 'simple_switch.py' iniciada correctamente"})
    except Exception as e:
        return JSONResponse({"message": f"Error al iniciar la aplicación: {str(e)}"}, status_code=500)

@app.post("/stop-ryu")
async def stop_ryu():
    global ryu_process
    try:
        if ryu_process is not None:
            # Acceder al canal de stdin y escribir el comando de salida 'exit'
            stdin, stdout, stderr = ryu_process
            stdin.write('exit\n')
            stdin.flush()

            # Cerrar los canales
            stdout.channel.close()
            stderr.channel.close()

            return JSONResponse({"message": "Aplicación 'simple_switch.py' detenida correctamente"})
        else:
            return JSONResponse({"message": "La aplicación no está en ejecución"}, status_code=400)
    except Exception as e:
        return JSONResponse({"message": f"Error al detener la aplicación: {str(e)}"}, status_code=500)
