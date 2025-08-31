import javax.script.*;

public class Calculator {
    public static void main(String[] args) throws Exception {
        javax.script.ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        java.util.Scanner sc = new java.util.Scanner(System.in);

        String exp = sc.nextLine();
        Object result = engine.eval(exp);
        System.out.println(result);
    }
}
