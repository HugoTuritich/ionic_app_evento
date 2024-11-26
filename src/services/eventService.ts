export const fetchEventoData = async () => {
    const url = "https://spvoluque.redambar.com.py/api3/ver_evento2.php?id=618782";
  
    try {
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });
  
      if (!response.ok) {
        throw new Error(`Error en la petición: ${response.status}`);
      }
      console.log('URL enviada:', url);
      const data = await response.json();
      return data; // Devuelve los datos al componente que lo llama
    } catch (error) {
      console.error('Error al obtener datos:', error.message);
      throw error; // Lanza el error para que lo maneje el componente
    }
  };