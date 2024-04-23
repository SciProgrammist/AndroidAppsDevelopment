# AndroidAppDevelopment

# Reducción del Tamaño de Carpeta Proyecto Android

## Descripción
Este repositorio contiene un Proyecto Android desarrollado en Android Studio. El propósito de este documento es proporcionar instrucciones sobre cómo reducir el tamaño de la carpeta del proyecto en caso de que supere los 20 megabytes (MB) cuando esté comprimida.

## Instrucciones
1. **Entrega de la Carpeta del Proyecto Android:** Se requiere entregar la carpeta del Proyecto Android desarrollado en Android Studio. No se aceptarán enlaces de Google Drive u otros servicios similares para esta entrega.

2. **Reducción del Tamaño:** Si la carpeta, incluso después de comprimirla, supera los 20 MB, siga estos pasos para reducir el tamaño del Proyecto Android:
    - Elimine las siguientes carpetas: `intermediates` y `outputs`.
    - Estas carpetas se encuentran ubicadas en la siguiente ruta: `carpeta-principal-proyecto-android/app/build`. Reemplace "carpeta-principal-proyecto-android" por el nombre real de su proyecto Android.

## Contenido de las Carpetas a Eliminar
- **intermediates:** Contiene archivos binarios del API de Android necesarios para compilar la aplicación. Si elimina estos archivos, el IDE de Android los volverá a copiar en la misma ubicación durante el proceso de compilación y ejecución del Proyecto Android.
  
- **outputs:** Esta carpeta contiene el archivo binario APK que se instalará en el dispositivo Android físico o virtual. Si elimina este archivo, el IDE de Android lo volverá a copiar en esta ubicación durante el proceso de compilación y ejecución del Proyecto Android.

## Nota Importante
Por favor, asegúrese de seguir estas instrucciones correctamente para garantizar el correcto funcionamiento del Proyecto Android después de reducir el tamaño de la carpeta.


