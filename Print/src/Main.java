import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Dispatcher dispatcher = new Dispatcher();
        int menu;
        String size, type;
        dispatcher.GetThread().start();
        while (true) {
            System.out.print("1. Добавление документа\n2. Отмена печати документ\n3. Список документ\n4. Остановка диспетчера\n5. Средняя продолжительность печати\n6. Выход\n");
            menu = in.nextInt();
            if (menu == 1) {
                if (dispatcher.GetThread().isAlive()) {
                    System.out.print("Выбирете размер документа: A6, A5, A4\n");
                    size = in.next();
                    System.out.print("Выберете тип документа: txt, docx, pdf\n");
                    type = in.next();
                    dispatcher.AddDocument(new Document(size, type));
                } else {
                    System.out.print("Диспетчер остановлен!\n");
                }
            }
            if (menu == 2) {
                dispatcher.GetThread().SetCancel(true);
            }
            if (menu == 3) {
                System.out.println("Очередь документов: ");
                for (Document doc : dispatcher.GetQueue()) {
                    System.out.println(doc.toString());
                }
            }
            if (menu == 4) {
                dispatcher.GetThread().SetAlive(false);
            }
            if (menu == 5) {
                if (dispatcher.GetGoodPrint().size() != 0) {
                    int k = 0;
                    long time = 0;
                    for (Document doc : dispatcher.GetGoodPrint()) {
                        k++;
                        time += doc.GetTime();
                    }
                    System.out.println("Среднее время печати документов: " + time / k + " мс");
                }
            }
            if (menu == 6) break;
        }
        dispatcher.GetThread().SetAlive(false);
        int j=0;
        while(j<dispatcher.GetQueue().size()){
            if(dispatcher.GetGoodPrint().contains(dispatcher.GetQueue().get(j))) {dispatcher.RemoveDocument(dispatcher.GetQueue().get(j));}
            else j++;
        }
        for(Document doc:dispatcher.GetQueue()){
            System.out.println(" удаленный документ: "+doc.toString());
        }
        for(Document doc:dispatcher.GetGoodPrint()){
            System.out.println(" напечатанный документ:  "+doc.toString());
        }
        for(Document doc:dispatcher.GetCancel()){
            System.out.println(" ? "+doc.toString());
        }
    }
}