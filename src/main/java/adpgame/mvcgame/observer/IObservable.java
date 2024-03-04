package adpgame.mvcgame.observer;


public interface IObservable {
    void registerObserver(IObserver observer, AspectEnum aspectEnum);
    void unregisterObserver(IObserver observer);
    void unregisterObserver(AspectEnum aspectEnum);
    void unregisterObserver(IObserver observer, AspectEnum aspectEnum);
    void notifyObservers(AspectEnum aspectEnum);
}
