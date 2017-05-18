# Useful Links

1. [Basis Library](http://sml-family.org/Basis/manpages.html)
2. [cmlib manpage](http://typesafety.net/cmlib/)
3. [cmlib github](https://github.com/standardml/cmlib)

# SML/NJ Cmdline
use readline in smlnj

    $ rlwrap sml

load heap image and execute script

    $ sml @SMLload=/path/to/heap/image
    - use "foo.sml";

check signature

    - map;

control print

    Control.Print.printLength := 1024;
    Control.Print.printDepth := 1024;
    Control.Print.stringDepth := 1024;

write string to file

    fun write fname str =
        let
            val fs = TextIO.openOut fname
        in
            TextIO.output (fs,str);
            TextIO.closeOut fs
        end;

use last result

    - it;

# Syntax

type

```` sml
type record = {size:int, name:hello} (% alias %)

datatype tree = Nil | node of tree * int * tree
datatype 'a list = nil | :: of 'a * 'a list (* 'a ~> alpha *)
datatype 'a option = SOME of 'a | NONE

abstype 'a set = null | ints of 'a * 'a set
with
    val emptyset = null
    val addset = ins
    fun memberset (x,null) = false
      | memberset (x,ins(v,s)) = x = v orelse memberset (x,s)
end
````

pattern

there is no guard in SML. as syntax in pattern matching

```` sml
case n of
    x as 0 => x
  | (1 | 2 | 3) => 1  (* multi-case *)
  | x as _ => x
````

char

```` sml
val c = #"a"
````

pair

```` sml
val foo = (1,2,3)
#1 foo
#3 foo
````

list

```` sml
val a = (~1)::[]
rev a
a @ b
````

record

```` sml
type record = {size:int, name:hello}
val foo = {size = 42, name = "hello"}
#size foo
````

vector

```` sml
val foo = #[1,2,3,4]
Vector.sub (foo,0)
````

function

```` sml
val id = fn x => x
val rec id = fn x => id x

fun id x = x

fun is always elaborated to a recursive function
SML has both recursive types and recursive functions.
But from PFPL, we known System PCF and System FPC are equivalent.

lambda x:t.e cannot be polymorphic, since we require G |- t : T
On the otherhand, Lambda a:k.c : Pia:k1.k2 which is not of kind T

Remember, lambda x:t.e is val. thus we can use it be kind of lazy
val lazy = fn () => (14 div 0);
lazy ();
````

function combination
```` sml
f o g
````

# Imperative SML

SML is not a pure functional language. It is a higher-order imperative language

raise and handle

```` sml
excpetion HelloError of string

raise HelloError "world"
handle e as HelloError s => raise Malformed
     | e as TypeError => raise TypeError
     | e as _ => raise e
````

sequential execution

```` sml
(e1;
e2;
e3)
````

reference

```` sml
val foo = ref 42;
foo := 24;
val bar = !foo;
````

while

```` sml
val foo = ref 42
val _ =
while !foo <> 0 do (
    foo := !foo - 1
)
````

Array

Library Array supports `fromList` and `fromVector`

Input/Output

Library TextIO supports input/output

# Module

overloading

Overloading in SML is second-class; it's first-class in Haskell

functors in SML are generative (always generate something new)

declaration w/o implementation

```` sml
structure StructureName = struct
    open List // import everything, polluate
    structure L = List // alias, import as
end
````

declaration, exported

```` sml
signature SIG_NAME = sig (* declarations *)
    exception TypeError

    type context
    type variable

    datatype con = .. | .. | ..

    val empty : context
    fun extendType : context -> variable -> con -> context
end
````

implementation, internal

```` sml
structure StructureName :> SIG_NAME = struct
    open List // import everything, polluate
    structure L = List // alias, import as
end
````
