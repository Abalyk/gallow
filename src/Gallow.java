import java.util.List;

public class Gallow {
    private int errors;
    private static final String fullGallow =
            "   -----\n" +
                    "   O   |\n" +
                    " --|-- |\n" +
                    "  / \\  |\n" +
                    "_______|";
    private final String gallow0 =
            "   -----\n" +
                    "       |\n" +
                    "       |\n" +
                    "       |\n" +
                    "_______|";
    private final String gallow1 =
            "   -----\n" +
                    "   O   |\n" +
                    "       |\n" +
                    "       |\n" +
                    "_______|";

    private final String gallow2 =
            "   -----\n" +
                    "   O   |\n" +
                    "   |   |\n" +
                    "       |\n" +
                    "_______|";
    private final String gallow3 =
            "   -----\n" +
                    "   O   |\n" +
                    " --|   |\n" +
                    "       |\n" +
                    "_______|";
    private final String gallow4 =
            "   -----\n" +
                    "   O   |\n" +
                    " --|-- |\n" +
                    "       |\n" +
                    "_______|";
    private final String gallow5 =
            "   -----\n" +
                    "   O   |\n" +
                    " --|-- |\n" +
                    "  /    |\n" +
                    "_______|";
    private final String gallow6 =
            "   -----\n" +
                    "   O   |\n" +
                    " --|-- |\n" +
                    "  / \\  |\n" +
                    "_______|";
    private List<String> gallows = List.of(gallow0,gallow1,gallow2,gallow3,gallow4,gallow5,gallow6);

    public Gallow() {
        errors = 0;
    }

    public void makeAnError() {
        errors++;
    }

    public int getErrors(){
        return errors;
    }

    public void printGallow() {
        if(errors<=6)
            System.out.println(gallows.get(errors));
    }

    public static void pringFullGallow() {
        System.out.println(fullGallow);
    }

}
