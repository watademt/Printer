public class Document {

    private Size size;

    private Type type;

    private long time;

    public Document(String size, String type){
        long n1 = 0, n2 = 0;
        if (size.equals("A6")){
            n1 = 100;
            this.size = Size.A6;
        }
        if (size.equals("A5")){
            n1 = 1000;
            this.size = Size.A5;
        }
        if (size.equals("A4")){
            n1 = 2000;
            this.size =Size.A4;
        }
        if (type.equals("txt")){
            n2 = 1;
            this.type = Type.txt;
        }
        if (type.equals("docx")){
            n2 = 2;
            this.type = Type.docx;
        }
        if (type.equals("pdf")){
            n2 = 3;
            this.type = Type.pdf;
        }
        this.time = n1*n2;
    }

    public long GetTime(){return this.time;}

    public String toString(){return (type + " " + size + " " + time);}

}
