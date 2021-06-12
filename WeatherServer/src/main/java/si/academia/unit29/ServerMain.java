package si.academia.unit29;

import org.apache.logging.log4j.LogManager;
import  org.apache.logging.log4j.Logger;

import si.academia.unit29.IWeatherApiProxy;
import si.academia.unit29.WeatherAdapter;
import si.academia.unit29.WeatherApiProxy;
import si.academia.unit29.IWeatherProvider;
import si.academia.unit29.WeatherService;
import si.academia.unit29.IWeatherService;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain implements Remote {
    private static final Logger log= LogManager.getLogger(ServerMain.class);
    public static void main(String[] args){
        log.info("..Starting Weather SERVER..");
        IWeatherApiProxy proxy=new WeatherApiProxy("http://api.weatherapi.com/v1/", "798bbc560c574b24bf984609211206");
        IWeatherProvider provider=new WeatherAdapter(proxy);
        WeatherService svc=new WeatherService(provider);

        try{
            log.info("Registering WeatherService");
            IWeatherService stub=(IWeatherService) UnicastRemoteObject.exportObject(svc, 0);
            Registry rmiReg= LocateRegistry.getRegistry();
            rmiReg.bind("WeatherService", stub);
            log.info("Server is ready!");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    log.info("Shutting server down!");
                    log.info("Unregistering WeatherService!");
                    rmiReg.unbind("WeatherService");
                    log.info("Server stopped!");
                } catch (RemoteException | NotBoundException e) {
                    e.printStackTrace();
                }
            }));
        }catch (RemoteException | AlreadyBoundException e){
            log.error("Failed to start server", e);
        }
    }
}
