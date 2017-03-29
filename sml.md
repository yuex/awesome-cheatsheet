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

use last result

    - it;

# Syntax

there is no guard in SML

as syntax in pattern matching

```` sml
case n of
    x as 0 => x
  | (1 | 2 | 3) => 1  (* multi-case *)
  | x as _ => x
````

record

```` sml
#size {size = 42, name = "hello"}
````

pair

```` sml
#3 (1,2,3)
````

char

```` sml
val c = #"a"
````

list

```` sml
val a = (~1)::[]
rev a
````

function combination
```` sml
f o g
````

sequential execution

```` sml
exception Malformed

(print ((Int.toString 10) ^ "\n")
 raise Malformed)
````

raise and handle

```` sml
excpetion HelloError of string

raise HelloError "world"
handle e as HelloError s => raise Malformed
     | e as TypeError => raise TypeError
     | e as _ => raise e
````

datatype and polymorphism

```` sml
datatype 'a list = nil | :: of 'a * 'a list (* 'a ~> alpha *)
datatype 'a option = SOME of 'a | NONE
````

# Module

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
