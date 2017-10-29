public class Main {
    public static void main(String[] args) {
        System.out.println(repeat('c',3));
    }

    // repeat char c n times to be a string
    public static String repeat(char c, int n) {
        String fmt = String.format("%%0%dd",n);
        return String.format(fmt,0).replace('0',c);
    }
}
