public class ThreadDocument extends Thread{

    private boolean cancel;
    private boolean alive;
    private boolean print;
    private Dispatcher dispatcher;

    public ThreadDocument(Dispatcher dispatcher){
        cancel = false;
        print = false;
        this.dispatcher = dispatcher;
    }

    public void SetPrint(boolean n){this.print = n;}
    public void SetAlive(boolean n){this.alive = n;}
    public void SetCancel(boolean n){this.cancel = n;}

    @Override
    public void run(){
        alive = true;
        int i = 0;
        int j = 0;
        while (alive){
            if(dispatcher.GetQueue().size() != 0){
                try{
                    Thread.sleep(dispatcher.GetQueue().get(0).GetTime());
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                if(!cancel) {
                    if (print) {
                        System.err.print("Документ напечатан: " + dispatcher.GetQueue().get(0).toString() + "\n");
                    }
                    dispatcher.AddGoodPrint(dispatcher.GetQueue().get(0));
                    dispatcher.RemoveDocument(dispatcher.GetQueue().get(0));
                }
                else {
                    if(print){
                        System.err.print("Документ отменен: " + dispatcher.GetQueue().get(0).toString() + "\n");
                    }
                     dispatcher.CancelDocument(dispatcher.GetQueue().get(0));
                }cancel = false;

            }
            else {
                try {
                    sleep(5000);
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
        }

    }
    }

}
