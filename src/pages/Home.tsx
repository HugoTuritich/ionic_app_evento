import React, { useEffect, useState } from 'react';
import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonList,
  IonItem,
  IonLabel,
} from '@ionic/react';
import { fetchEventoData } from '../services/eventService'; // Asegúrate de haber creado este archivo

import './Home.css';

const Home: React.FC = () => {
  const [eventoData, setEventoData] = useState<any>(null);
  const [invitados, setInvitados] = useState<any[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const loadEventoData = async () => {
      try {
        const data = await fetchEventoData();
        setEventoData(data);

        if (data.invitados_acreditados) {
          setInvitados(data.invitados_acreditados);
        }
      } catch (err: any) {
        setError(err.message);
      }
    };

    loadEventoData();
  }, []);

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Evento</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Evento</IonTitle>
          </IonToolbar>
        </IonHeader>
        {error ? (
          <p>Error al cargar los datos: {error}</p>
        ) : (
          <div>
            <h1>{eventoData?.nom_evento}</h1>
            <h2>{eventoData?.nom_local}</h2>
            <h3>{eventoData?.fecha}</h3>

            <IonList>
              {invitados.map((invitado, index) => (
                <IonItem key={index}>
                  <IonLabel>
                    <h2>{invitado.nom_inv}</h2>
                    <p>{invitado.nom_acre}</p>
                  </IonLabel>
                </IonItem>
              ))}
            </IonList>
          </div>
        )}
      </IonContent>
    </IonPage>
  );
};

export default Home;
