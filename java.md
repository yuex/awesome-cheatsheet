# Java

An application language, not a script language or a system languge

Static method does not operate on any objects

A package is a set of related classes

In java, everything is declared inside no class. No global variable and
function. This can lead to somewhat verbose code.

    //     inline comment
    /* */  multi-line comment
    /** */ document comment

File name must be same with class name

    javac /package/path/Foo.java
    java package.path.Foo

integer

    byte  1    int   4
    short 2    long  8
    Integer.MIN_VALUE / MAX_VALUE
    Byte, Short, Long class also have these two values

    bigger than long: java.math.BigInteger

    one_million = 1_000_000
                = 0b1111_0100_0010_0100_0000
    all integers are signed, call Byte.toUnsignedInt(b) to do conversion

floating-point

    float  = 3.14F
    double = 3.14D
    0x1.0p-10 // hex floating-point number uses p instead of e

    Float|Double.POSITIVE_INFINITY|NEGATIVE_INFINITY|NaN
    every NaN is different, check using = will fail
    use isNaN(x) | isInfite(x) | isFinite(x)

    precise numerical w/o roundoff: java.math.BigDecimal

char and bool

    char UTF-16 encoding
    'J', '\u004A', '\'', '\\'

    boolean := false | true

variable

    int total = 0;
    int total = 0, count; // count is uninitialized int
    Random gen = new Random();

    name starts with letter, can contain _ and $
    but $ is conventionally used for auto-generated names
    but letters and digits can be from any alphabet, like lambda and pi

    conventionally, a variable is declared as late as possible,
    just before you need it for the first time

constant

    final int DAYS_PER_WEEEK = 7;
    final: like const keyword in C
    once it is initialized, it is final (cannot be changed)
    but this initilization can happen later than declaration

operators

    >> right shift with 0
    >>> right shift with sign bit
    n >> k, k is moduloed first to be with range of sizeof(n)
    k = [0,31]

    int n = 1;
    n << 31 => Integer.MIN_VALUE
    n << 32 => 1
    n << 33 => 2

big number

    java.math.BigInteger
    java.math.BigDecimal

    BigInteger n = BigInteger.valueOf(42L)
    BigInteger n = new BigInteger("42")

    java has no operator overloading
    // 5 * (n + k)
    BigInteger r = BigInteger.valueOf(5).multiply(n.add(k))

    BigDecimal.valueOf(n,e) // nx10-e
    BigDecimal.valueOf(2,0).substract(BigDecimal.valueOf(11,1))



