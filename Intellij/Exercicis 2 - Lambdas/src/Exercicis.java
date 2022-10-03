import java.math.BigDecimal;
import java.util.Map;
import java.util.function.*;

public class Exercicis {

    /**
     * Torna un supplier que sempre torna "Hello"
     */
    public static Supplier<String> helloSupplier() {
        Supplier<String> supplier = () -> "Hello";

        return supplier;
    }

    /**
     * Torna un Predicate que mira si l'string és buit
     */
    public static Predicate<String> isEmptyPredicate() {
        Predicate<String> predicate = (s) -> s.length() == 0;

        return predicate;
    }

    /**
     * Torna un Function que accepta un String que retorna aquest string repetit
     * n vegades, on n es passa com a argument de la funció.
     */
    public static BiFunction<String, Integer, String> stringMultiplier() {
        BiFunction<String, Integer, String> biFunction = (s, n) -> s.repeat(n);

        return biFunction;
    }

    /**
     * Torna un Function que converteix un BigDecimal a String que comença pel símbol "$"
     */
    public static Function<BigDecimal, String> toDollarStringFunction() {
        Function<BigDecimal, String> function = (n) -> "$" + n.toString();

        return function;
    }

    /**
     * Es reben dos paràmetres que representen un rang (min,max) i retorna
     * un Predicate<String> que verifica si la longitud de l'string està
     * dins aquest rang.
     */
    public static Predicate<String> lengthInRangePredicate(int min, int max) {
        Predicate<String> predicate = (s) -> min <= s.length() && s.length() <= max;

        return predicate;
    }

    /**
     * Retorna un Supplier de números enters aleatoris
     */
    public static IntSupplier randomIntSupplier() {
        IntSupplier supplier = () -> (int) (Math.random() * 100);

        return supplier;
    }

    /**
     * Retorna un IntUnaryOperator que reb un int com un límit que a la vegada
     * retorna un número aleatori dins aquest límit
     */
    public static IntUnaryOperator boundedRandomIntSupplier() {
        IntUnaryOperator intUnaryOperator = (n) -> (int) (Math.random() * n);

        return intUnaryOperator;
    }

    /**
     * Retorna un IntUnaryOperator que calcula un quadrat d'un número
     */
    public static IntUnaryOperator intSquareOperation() {
        IntUnaryOperator intUnaryOperator = (n) -> n * n;

        return intUnaryOperator;
    }

    /**
     * Retorna un LongBinaryOperator que realitza l'operació de suma
     */
    public static LongBinaryOperator longSumOperation() {
        LongBinaryOperator longBinaryOperator = (n, i) -> n + i;

        return longBinaryOperator;
    }

    /**
     * Retorna un ToIntFunction<String> que converteix un String a un Integer
     */
    public static ToIntFunction<String> stringToIntConverter() {
        ToIntFunction<String> toIntFunction = (s) -> Integer.parseInt(s);

        return toIntFunction;
    }

    /**
     * Reb un paràmetre n (int), i retorna un Supplier que proporciona un IntUnaryOperator
     * que realitza la funció f(x) = n * x
     */
    public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
        Supplier<IntUnaryOperator> supplier = () -> (x) -> n * x;

        return supplier;
    }

    /**
     * Retorna una funció que composa funcions amb la funció trim() de String
     */
    public static UnaryOperator<Function<String, String>> composeWithTrimFunction() {
        Function<String, String> function = s -> s.trim();

        UnaryOperator<Function<String, String>> unaryOperator = (f) -> f.apply().trim();

        return unaryOperator;
    }

    /**
     * Reb un Runnable com a paràmetre i retorna un Supplier<Thread>
     * Aquest thread s'iniciarà quan es cridi al mètode "get()" del supplier.
     */
    public static Supplier<Thread> runningThreadSupplier(Runnable runnable) {

        return null;
    }

    /**
     * Retorna un Consumer que accepta un Runnable com a paràmetre i l'executa
     * dins un nou fil (thread)
     */
    public static Consumer<Runnable> newThreadRunnableConsumer() {

        return null;
    }

    /**
     * Retorna una Function que accepta una instància d'un Runnable i retorna
     * un Supplier d'un Thread que s'ha creat per aquest Runnable.
     */
    public static Function<Runnable, Supplier<Thread>> runnableToThreadSupplierFunction() {

        return null;
    }

    /**
     * Retorna una BiFunction que té 2 paràmetres.
     *
     * El primer serà un IntUnaryOperator, que és una funció "integer".
     *
     * El segon serà un IntPredicate, que és una condició sobre un integer.
     *
     * Aquesta BiFunction tornarà una funció composada, que farà el següent:
     *
     * - Si el IntPredicate verifica la seva condició, aplicarà la funció
     * - Si el IntPredicate no es compleix, retorna el mateix element que s'ha rebut
     */
    public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> functionToConditionalFunction() {

        return null;
    }

    /**
     * Torna una BiFunction, on el primer paràmetre és un un Map (la clau és una nom d'una funció
     * i el valor és una funció tipus IntUnaryOperator), el segon paràmetre és un String, que és
     * també el nom d'una funció.
     *
     * Si el Map conté una funció amb el nom del segon paràmetre, aleshores es torna la funció
     * emmagatzemada al map. Si no hi és, tornem la funció "identity()", que no és més que una
     * funció que retorna el mateix paràmetre que li passem.
     */
    public static BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader() {

        return null;
    }

    /**
     * Retorna un Supplier d'un Supplier d'un Supplier de l'string "BEN FET!"
     */
    public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier() {
        return null;
    }
}
