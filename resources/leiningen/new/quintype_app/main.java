package {{namespace}};

public class main {
  public static void main(String argv[]) {
    String[] newArgs = new String[argv.length + 2];
    newArgs[0] = "-m";
    newArgs[1] = "{{namespace}}.core";
    System.arraycopy(argv, 0, newArgs, 2, argv.length);
    clojure.main.main(newArgs);
  }
}
