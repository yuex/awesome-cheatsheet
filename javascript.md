# JavaScript

`==` and `!=` will do type coerce. `===` and `!==` won't

switch and typeof. case is compared using `===`

    function detectType(value) {
        switch(typeof value) {
            case 'string':
                return 'str';
            case 'number':
                return 'num';
            case 'object':
                return 'obj';
            default:
                return 'other';
        }
    } // implicitly return the undefined value

function declarations. two form

    // function statement
    function foo() {
    }

    // function expression, could be used to define local function
    var bar = function() {
    };

string

    str.length
    str.replace(pat,str) // first occurence
    str.indexOf(substr) !== -1 // predicate: if substr in str
    str.trim
    str.substring(start, length)

only one type for numbers, no matter what it is, integer, float, double

    Math.round((.1 + .2) * 100) / 100 === 0.3
    typeof NaN === 'number'
    0 / 0 is NaN
    5 / 0 is infinity
    every instance of NaN is different, check with isNaN(0/0)

An object in JavaScript is a set of key-value pairs, also known as a map. The
keys are strings and the values can be anything (other objects or primitives).

    var otherPerson = {
        name: 'Zach',
        weight: 800,
        speakFunction: function() {
            return "hello";
        }
    }; // literal syntax

    var otherConstructed = new Object(); // new syntax

    person.age = 30;       // dot notation
    person['height'] = 70; // bracket notation

access to undefined obj instance will throw exception. but access to undefined
member will return the undefined value normally. but but access to the
properties or methods of a undefined value will throw exception

    if (person && person.name)
        trimmedNmae = person.name.trim()

for iteration

    var arr = []
    for (var i = 0; i < arr.length; i++)
        // i is index
    for (var key in arr)
        // key is key or index

array in JavaScript is heterogeneous. array operations

    arr.length
    arr.indexOf(element) == -1
    arr = [0,1,2,3]
    arr[7] = 7 // cause 4-6 to be of value undefined
    delete arr[2] // cause arr[2] to be undefined
    arr.splice(start_index, number_to_remove) // really delete something

when `var` is omitted when declaring variable, a global variable is
created. this is considered a bad practice

    obj.hasOwnProperty(key) == true
    // hasOwnProperty is inherited from the meta object Object

semicolon is inserted at the end of every line automatically. this may cause
some serious problems like

    return
        {
            oh: "dear"
        };
    // will return undefined

trailing commas in array literals may cause some browsers to crash

since functions are first-class in JavaScript, you can define one-time functions
in following ways.

    (function() {
    })('some param');

    (function() {
    }('some param'));

    !function() { // looks nicer
    }('some param');

    // could also be named
    (function named() {
    })('some param');

But usually this is used as a way to do namespace separation.  jQuery wraps the
entire library in one self-invoking function

    (function( window, undefined ) {

        // locals objects inside the function
        var document = window.document;
        var jQuery;

        // Lots of cool code here that defines jQuery.

        // Expose jQuery to the global object
        window.jQuery = jQuery;
        window.$ = jQuery;

    })(window);

Another way is to define one or two global object for the entire application.
and then put everything else in those global variables. Many organizations
prefer this way, including Google

    // One way to use this technique.
    var myApp = {};

    myApp.utils = {};
    myApp.settings = {};

    myApp.utils.trim = function() {
    };

    // Another example - the technique is the name, but
    // some people prefer this syntax.
    var myOtherApp = {
        utils: {
            trim: function() { /* ... */ }
        },
        settings: {
            // ...
        }
    };

interactive

    alert("hello");
    confirm(msg) == true;
    prompt(msg) == string;
    console.log(msg) // print

# The Modern JavaScript Tutorial

## Misc

developer console in Chrome `cmd+opt+j`

embed the script in html

    <!DOCTYPE HTML>
    <html>
    <body>
        <p>Before the script...</p>
        <script>
            alert( 'Hello, world!' );
        </script>
        <p>...After the script.</p>
    </body>
    </html>

refer to external js. once `src` is set, the script content is ignored

    <script src="/path/to/script.js"></script>

in most cases, js will auto-insert semicolons. but there are cases it won't.  so
always add the semicolons to avoid confusion and errors.

comments `//` and `/* */`

js has experienced a major re-design (ECMAScript5, ES5). but to provide backward
compatibility, it is turned off by default. to turn it on, `use strict`. it must
be put on top of the script and be active to the end. though it could be put
just before a function and be active through the decorated function, it is not a
widely adopted practice to do so. it is recommended to always turn it on

multiple variables declaration, three tastes

    let user = 'John';
    let age = 25;
    let message = 'Hello';

    let user = 'John',
        age = 25,
        message = 'Hello';

    let user = 'John'
      , age = 25
      , message = 'Hello';

    const myBirthday = '18.04.1982'; // make things unchangable
    // var is old-school, there's some subtle differences

difference with `var`

1. `var` has no block scope. either function-scoped or global. `var` defined in
   block will leak outside
2. `var`s are processed at the function start. Declarations are hoisted, but
   assignments are not.



## Types

numbers in js are all float. thus there are special numbers like 'Infinity',
`-Infinity`, `NaN`.

    1 / 0 === Infinity
    "hello" / 0 === NaN
    // the math operations are closed in this setting

string provides template support by using backquote. expression inside `${...}`
will be evaluated and then coerced to string. BTW, there's no character in js.

    let str = "Hello";
    let str2 = 'Single quotes are ok too';
    let phrase = `can embed ${str}`;
    let phrase = `can embed ${3 * 4}`;

`null` and `undefined` in js have types of their own. but in practice, `null` is
used as a normal value. `undefined` is only used to check against js.

`typeof` reveals the type of a value. it's a operator but can be used as a
function like `sizeof` in C.

## Coercion

most math operations will coerce number correctly. but `+`
is also a valid string operator. when one argument is string, the result will be
string concatenation instead of math addition.

when being type coerced, `null` becomes `0`. `undefined` becomes `NaN`

`true` is 1; `false` is 0. But when coercing from string to boolean, only empty
 string `""` is `false`. anything else is `true`

A fucking example for js. `===` (no coercion) solves part of the problem

    let a = 0;
    let b = "0";
    Boolean(a); // == false, number -> boolean
    Boolean(b); // == true,  string -> boolean
    a == b;     // == true!  string -> number

bottom line: don’t use comparisons `>= > < <=` with a variable which may be
`null` or `undefined`, unless you are really sure what you’re doing. If a
variable can have such values, then check for them separately.

## Interaction

needs a browser. won't work in NodeJS

    alert(msg);
    result = prompt(msg[, default]);
    // in IE, if you don't specify the second arg, undefined is always used
    // then you need to specify it always, fuck MS
    result = confirm(question);

## If and Ternary

multiple ternary

    let message = (age < 3) ? 'Hi, baby!' :
        (age < 18) ? 'Hello!' :
        (age < 100) ? 'Greetings!' :
            'What an unusual age!';

switch is carried out by `===`

## Looping with Labels

`while`, `for`, `do...whlie` all supported. inside a loop, `break` and
`continue` can be used. `break` and `continue` also support labels. but those
labels can only be loops. it's not an arbitrary goto label. only used to break
out of nested loops

    outer: for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        let input = prompt(`Value at coords (${i},${j})`, '');
        if (!input)
            break outer; // (*)
            // continue outer;
      }
    }

## Functions

declaration and invocation.

    function showMsg(from, msg) {
        console.log(`${from}: ${msg}`);
    }

    showMsg("Brian") // Brian: undefined

    function showMsg(from, msg = "hello")
    showMsg("Brian") // Brian: hello
    // old js doesn't support default value, some hacks are used to do so
    // msg = msg || "hello"
    // msg = (msg === undefined) ? "hello" : msg

A function with an empty return or without it returns undefined. An empty return
is also the same as return undefined

in js, functions are first-class. it's called function expression

    let showMsg = function(from, msg) {
        console.log(`${from}: ${msg}`);
    }; // do not forget this semicolon

arrow functions. you may think it's lambda. but it's not true exactly, because
statements are allowed as long as enclosed in `{}`. it's more like lambda in
modernized algol because cmd and bnd are used here

    let sum = (a,b) => a + b;
    let sum = (a,b) => {
        let result = a + b;
        return result;
    };

function declaration is allowed in blocks `{}` to provide local functions. and
js doesn't require the declarations to appear before invocation. but functions
declared later will shadow functions declared earlier with same name. when this
two rules combined, there could be some really confusing behaviors.

## Code Quality

use `debugger` statement to stop the execution of js

style guide: [Google's Style Guide][]

style checkers (linters): eslint

    npm install -g eslint
    vim ~/.eslintrc

Good comments (JSDoc, js' doc framework, similar to Doxygen for C)

1. describe the architecture
2. document the function usage
3. why the task is implemented in this way
4. Anything subtle and count-intuitive? Where?

Bad comments explain what's going on or how it is implemented

test framework [Mocha][], [Chai][], [Sinon][]

Standardization procedure, [propose][], [specification][], [support status][]

[Babel][] is a transpiler to convert modern js to previous standards

## Object

basically, it's a dict acts as an object

    let user = new Object(); // "object constructor" syntax
    let user = {};  // "object literal" syntax
    let user = {     // an object
        name: "John",  // by key "name" store value "John"
        age: 30        // by key "age" store value 30
    };
    delete user.age;
    let fruit = 'apple';

    // user computed property name
    let bag = {
        [fruit]: 5,
        ['apple' + 'Computers']: 5 // bag.appleComputers = 5
    };

    // shorthanded property name
    function makeUser(name, age) {
        return {
            name, // same as name: name
            age   // same as age: age
        };
    }

key iteration.

    key in user // check for key existence
    for(key in user) // iterate through keys, but key will be seen outside for
    for(let key in user) // key won't be seen outside

the order is special:

1. integer properties are sorted
2. others appear in creation order

integer properties are keys which can be changed to and from integers without
change. for example, `"42"` is. but `"4.0"`, `"+4"` are not

primitive types are copied by value. objects are copied by reference. copy by
value for objects are provided by

    Object.assign(dest[, src1, src2, src3...])
    // copies all properties of src1, src2, ... into dest

but this is a shallow copy. deep copy is not provided in js. use 3rd-party lib
like [lodash][]'s `_.cloneDeep(obj)` as in [https://lodash.com/docs#cloneDeep](https://lodash.com/docs#cloneDeep)

## Symbol

by spec, property keys of objects have to be either of string type or symbol
type. symbols with same names are different

    Symbol("id") === Symbol("id") // false

one implication is that property name created by symbol could never be
overwritten. because have no way to it.

dot notation will be converted to string. but bracket notation supports both
string type and symbol type.

symbols do not participate in `for..in`. and thus called hidden properties

global symbol registry. sometimes, global symbols are needed. symbols with same
name should always be same. this is provided by

    let sym = Symbol.for("name"); // if the symbol did not exist, it is created
    let symAgain = Symbol.for("name");
    sym === symAgain; // true

    let name = Symbol.keyFor(sym); // get name by symbol

there are also system symbols defined by js and accessible via `Symbol.*`

## This

use `this` in the object method to refer to data stored in the same object.
`this` is resolved at runtime. in non-strict mode, if this cannot be resolved,
it will be bound to the top-level object, the `window` in a browser. this
behavior is fixed by strict mode.

but the `this` binding is only passed by `obj.method()`. it's not reserved when
uusing the `obj.method` as a first-class citizen. also, arrow functions have no
`this`. what a mess

## toPrimitive

convert object to primitives. object are always true as a boolean

    obj[Symbol.toPrimitive] = function(hint) {
        switch(hint) {
        case "string":
            break;
        case "number":
            break;
        case "default":
            break;
        }
    }

historically, if `Symbol.toPrimitive` doesn't exist, `toString()` will be called
if hint is `"string"`; `valueOf()` will be called if hint is `"number"` or
`"default"`;

In practice, it’s often enough to implement only `obj.toString()` as a
“catch-all” method for all conversions that returns a “human-readable”
representation of an object, for logging or debugging purposes.

## new constructor

constructor function is regular function with some conventions. `new` will
decorate it to return `this`

    function User(name, age) {
        this.name = name
        this.age  = age
    }

    let user = new User("John", 30);

inside the constructor function, `new.target` is a boolean to indicate if this
function is called with `new`. it is usually used to redirect regular function
call to new constructor.

    function User(name) {
        if (!new.target) {
            return new User(name);
        }
        this.name = name;
    }

    let user = User("John"); // will be redirected to new User()

usually, constructor functions do not return. but return statement is allowed.
the rule is simple, primitive return is ignored. object is returned instead of
`this`

methods are allowed in constructor functions.

    function User(name) {
        this.name = name
        this.sayHi = function() {
            console.log(this.name);
        };
    }

## Primitives

primitives are low-level, efficient implementation. but JavaScript decided to
make them looks like objects. whenever a method on a primitive type is called, a
wrapper object is created using that primitive. the method is called. then the
wrapper object is discarded.

numbers

    num.toString(base)
    num.toFixed(number_of_precision_digit)
    isFinite(num)
    isNaN(num)

string. backquote could span multiple lines. the newline will be reserved. but
single and double quote cannot. strings are immutable and coded in UTF16

    str.legnth
    str.toUpperCase()
    str.toLowerCase()
    str.indexOf()
    str.lastIndexOf() // search from the end
    str.includes(pat[,pos]) // pat in str[pos:]
    str.startsWith()
    str.endsWith()
    str.slice(start[,end]) // get a substring, could be negative, from end
    str.substring(start[,end]) // start could be greater than end
    str.substr(str[,length])
    str.codePointAt(pos) // unicode of str[pos]
    String.fromCodePoint(code)
    str.split(sep)

## Arrays

arrays are heterogenerous object

    let arr = new Array();
    let arr = [];
    arr.pop() // pop from end
    arr.push(elem) // push to end
    arr.shift() // pop from head
    arr.unshift(elem) // push to head

loop throught arrays

    for (let i = 0; i < arr.length; i++)
    for (let val of arr) // iterates through values

`for (let key of arr)` is a bad idea. because it iterates through all properties
it is optimized for general objects instead of arrays.

`arr.length` is writable. increase it cause nothing. but decrease it will
truncate the array

    new Array(num) // create array of given size
    new Array(0,1,2) // [0,1,2]
    arr.fill(elem) // fill all elements with elem
    delete arr[pos]
    arr.splice(index[, deleteCount, elem1, ..., elemN])
    // start from index, deleteCount, and replace them with elem1..elemN
    // no holes. deleted items are returned as array
    // negative index indicates end
    arr.slice(start,end)

    arr.concat(arr1,arr2,...)
    // array-like object is concated as a whole.
    // unless obj[Symbol.isConcatSpreadable] === true, elements are concated

    arr.indexOf(elem[,from])
    arr.lastIndexOf(elem[,from])
    arr.includes(elem[,from])

    arr.find(function(item,index,array){return false})
    // function called for each element.
    // if return true, search stops and return the element
    arr.findIndex(fun(e,i,a){}) // return index
    arr.filter(fun(e,i,a){})
    arr.map(fun(e,i,a){})

    arr.sort(compare(a,b){}) // in-place
    arr.sort((a,b) => a - b) // sort with arrow function

    str.split(sep)
    arr.join(sep)

## Map, Set, and WeakMap, WeakSet

map allows keys of any type. object allows only string and symbol. map can also
use object as keys.

    new Map() – creates the map.
    map.set(key, value) – stores the value by the key.
    map.get(key) – returns the value by the key.
    map.has(key) – returns true if the key exists, false otherwise.
    map.delete(key) – removes the value by the key.
    map.clear() – clears the map
    map.size – is the current elements count.

map use `===` to compare keys except that `NaN`s are considered all same

iteration

    map.keys() – returns an iterable for keys,
    map.values() – returns an iterable for values,
    map.entries() – returns an iterable for entries [key, value], it’s used by default in for..of.
    recipeMap.forEach( (value, key, map) => {
        alert(`${key}: ${value}`); // cucumber: 50 etc
    });

Set

    new Set(iterable) – creates the set, optionally from an iterable object
    set.add(value) – adds a value, returns the set itself.
    set.delete(value) – removes the value, returns true if value existed
    set.has(value) – returns true if the value exists in the set
    set.clear() – removes everything from the set.
    set.size – is the elements count.

iteration. 2nd arg is same as 1st arg, to keep compatibility with map

    set.forEach((value, valueAgain, set) => {
        alert(value);
    });
    set.keys() – returns an iterable object for values,
    set.values() – same as set.keys, for compatibility with Map,
    set.entries() – returns an iterable object for entries [value, value], exists for compatibility with Map.

WeakMap and WeakSet are similar to Map and Set except that weak ones do not
prevent js from gc its contents (keys or values). They are ususally used to
maintain additional infomation for the main objects. When the main objects do
not have references any more, they could be automatically collected from the
weak ones.

js provides some general iteration mechnism. as long as the obj is iterable

    Object.keys(obj) – returns an array of keys.
    Object.values(obj) – returns an array of values.
    Object.entries(obj) – returns an array of [key, value] pairs.

    for(let value of Object.values(user)) {
        alert(value); // John, then 30
    }

## DeStructuring - Simple Pattern Matching

array matching

    let [a,b,c] = [0,1,2]
    let [ , ,c] = [0,1,2]
    for(let [k,v] of arr.entries())
    let [a,b,...c] = [0,1,2,3,4,5] // ...c all that remains, rest operator
    let [a,b] = [] // use default values - undefined

default values can be specified to be more complicated expressions.

    let [name="Guest", surname="Anonymous"] = ["Julius"]
    let [name=prompt('name?'), surname=prompt('surname?')] = ["Julius"];

object matching

    let options = {
        title: "Menu",
        width: 100,
        height: 200
    };
    let {title, width, height} = options;
    let {width: w, height: h, title} = options; // source:target
    let {width=100, height=200, title} = options; // default values
    let {width:w=100, height:h=200, title} = options; // combine
    let {title, ...rest} = options; // rest operator

without let, parentheses are necessary. because otherwise `{}` will be treated
as blocks

    ({title, width, height} = options); // without let, pa

it could be nested. it could be used in function declaration to simplify calls
with optional arguments

    let options = {
        title: "My menu",
        items: ["Item1", "Item2"]
    };

    // argument matching
    function showMenu({title = "Untitled", width = 200, height = 100, items = []}) {
        alert( title + ' ' + width + ' ' + height );
        alert( items );
    }

    showMenu(options);
    showMenu({})
    showMenu() // valid only when all matchings have default values

## Date

create

    new Date() // use current date and time
    new Date(ms) // epoch time
    new Date("2017-06-11") // YYYY-MM-DDTHH:mm:ss.sssZ, Z for zone, +-hh:mm
    new Date(year,mon,day,hrs,min,sec,ms)

set and get. every one except `setTime()` has a UTC counterpart, `setUTCHours()`

    setFullYear(year [, month, date])
    setMonth(month [, date])
    setDate(date)
    setHours(hour [, min, sec, ms])
    setMinutes(min [, sec, ms])
    setSeconds(sec [, ms])
    setMilliseconds(ms)
    setTime(milliseconds)

when number is to be coerce, epoch time is used.

quick way to get epoch time `Date.now()`

## JSON

JavaScript Object Notation, RFC 4627, format to convert objects into strings

    JSON.stringify to convert objects into JSON.
    JSON.parse to convert JSON back into an object.

The resulting json string is a called JSON-encoded or serialized or stringified
or marshalled object

JSON is data-only cross-language specification, so language specific properties
and functions are not included.

The important limitation: there must be no circular references. will cause
conversion error

    JSON.stringify(value[, replacer, space])
    // value - A value to encode.
    // replacer - Array of properties to encode or a mapping function function(key, value).
    // space - Amount of space to use for formatting
    // object could define its own `toJSON()` methods, `JSON.stringify()` will call it

    JSON.parse(str[, reviver]);
    // str - JSON-string to parse.
    // reviver - Optional function(key,value) that will be called for each
                 (key,value) pair and can transform the value.

## Advanced Functions Parameters

    function (arg1, arg2, ...args) {
        for (let arg of args) {
        }
    }

functions also have a special parameter called `arguments` which is an
array-like iterable object contains all arguments by their indexes

spread, expand iterable object to functions taking rest parameters. use
iterators to get elements internally, same way as `for...of`

    let arr1 = [1, -2, 3, 4];
    let arr2 = [8, 3, -8, 1];
    Math.max(...arr1, ...arr2) // combine
    Math.max(1, ...arr1, 2, ...arr2, 25) // mix with normal values
    let merged = [0, ...arr, 2, ...arr2]; // merge into other array

    [..."Hello"] // H,e,l,l,o, works with any iterable
    Array.from("Hello") // could also use Array from

difference between `Array.from(obj)` and `[...obj]`:

* `Array.from` operates on both array-likes and iterables.
* The spread operator operates only on iterables.

`...` is used for both rest operator and spread operator

In js, functions are objects.

    function sayHi() {
        alert("Hi");
    }
    sayHi.name // sayHi
    sayHi.length // number of parameters
    sayHi.counter = 0 // create custom property

use `new` to create a function. parameters go first, body goes last. all
strings.  one thing is that you can use this api to transmit functions between
server and client

    let sum = new Function('a', 'b', 'return a + b');

regular function calls doesn't bind `this` explicitly. following methods can do
this. this can be useful when writing decorator for object methods

    // bind context to this
    func.call(context, arg1, arg2, ...)
    func.call(context, args)

    let args = [1, 2, 3];

    func.call(context, ...args); // pass an array as list with spread operator
    func.apply(context, args);   // is same as using apply

when passing object method around (`setTimeout`), it's easy to lose `this` since
it's automatically bound to the object instead of the method. one way to sovle
this problem is manual binding

    let user = {
        firstName: "John",
        say(phrase) {
            alert(`${phrase}, ${this.firstName}!`);
        }
    };

    let say = user.say // lose this
    let say = user.say.bind(user);

    func.bind(context, arg1, arg2, ...); // bind both this and args, curry

## Scheduling

`setTimeout()` execute after timeout

    let timerId = setTimeout(func|code, delay[, arg1, arg2...])
    clearTimeout(timerId); // cancel it

`setInterval()` execute periodically

    let timerId = setInterval(func|code, delay[, arg1, arg2...])
    clearInterval(timerId); // cancel it

another way to execute sth periodically is to run `setTimeout` recursively.

    let timerId = setTimeout(function tick() {
        alert('tick');
        timerId = setTimeout(tick, 2000); // (*)
    }, 2000);

but `setInterval` keeps the delay between every invocation. recursive
`setTimeout` keeps the delay between call end and call start

`setTimeout(func,0)` won't spawn infinte number of instance instantly. it spawns
new instance only after the first one has finished. this is usually used to
split CPU-hungry task. force preemption

    let i = 0;
    let start = Date.now();
    function count() {
        do {// do a piece of the heavy job (*)
            i++;
        } while (i % 1e6 != 0);

        if (i == 1e9) {
            alert("Done in " + (Date.now() - start) + 'ms');
        } else {
            setTimeout(count, 0); // schedule the new call (**)
        }
    }

    count();

## Accessor Properties

property flags

    let descriptor = Object.getOwnPropertyDescriptor(obj, property_name)
    Object.defineProperty(obj, propertyName, descriptor)
    {
        "value": "John",
        "writable": true, // if true, can be changed, otherwise it’s read-only.
        "enumerable": true, // if true, then listed in loops, otherwise not listed.
        "configurable": true // if true, the property can be deleted and these attributes can be modified, otherwise not.
    }
    Object.defineProperty(user, "name", {
        writable: false
    });

    // define several properties at once
    Object.defineProperties(obj, {
        prop1: descriptor1,
        prop2: descriptor2
        // ...
    });

Property descriptors work at the level of individual properties. There are also
methods that limit access to the whole object:

    Object.preventExtensions(obj) - Forbids to add properties to the object.
    Object.seal(obj) - Forbids to add/remove properties, sets for all existing
                       properties configurable: false.
    Object.freeze(obj) - Forbids to add/remove/change properties, sets for all
                         existing properties configurable: false, writable: false.
                         And also there are tests for them:

    Object.isExtensible(obj) - Returns false if adding properties is forbidden, otherwise true.
    Object.isSealed(obj) - Returns true if adding/removing properties is forbidden,
                           and all existing properties have configurable: false.
    Object.isFrozen(obj) - Returns true if adding/removing/changing properties is forbidden,
                           and all current properties are configurable: false, writable: false.

## Getters and Setters

getters and setters create virtual properties

    let user = {
        name: "John",
        surname: "Smith",

        get fullName() {
            return `${this.name} ${this.surname}`;
        }
        set fullName(value) {
            [this.name, this.surname] = value.split(" ");
        }
    };
    alert(user.fullName); // John Smith
    user.fullName = "Alice Cooper";

accessor descriptors of getters and setters are different

    get – a function without arguments, that works when a property is read,
    set – a function with one argument, that is called when the property is set,
    enumerable – same as for data properties,
    configurable – same as for data properties.

getters and setters could be used to

* restrict values that will be accepted, like reject usernames which are too short
* provide compatibilities. internal properties could change, but external apis stay same
* keep a single internal representation of data, provide different
  interpretation via apis, like birthday, age

## Prototype

In JavaScript, objects have a special hidden property `[[Prototype]]`, that is
either null or references another object.

`[[Prototype]]` could be accessed by `obj.__proto__`. but in old times, it is
accessed by constructor's `prototype` property. when create a new object from
that constructor, `[[Prototype]]` will be set automatically

note, prototype must be a reference to an object instance, not a constructor

    function A() {
        this.a = "a";
    }

    function B() {
        this.__proto__ = new A();
        this.b = "b";
    }

    // B.prototype = new A();

there are two ways to layout data and methods in a class

    function User(name) { // functional pattern
        this.name = name;
        this.sayHi = function() {
            console.log(`Hi, ${this.name}`);
        };
    }

    function User(name) {
        this.name = name;
    }

    User.prototype.sayHi = function() { // prototypal pattern
        this.sayHi = function() {
            console.log(`Hi, ${this.name}`);
        };
    }

    // B.prototype.__proto__ = A.prototype;

the prototypal pattern is more memory-efficient since the method is not bind to
every object instace. it's also more friendly to class inheritance.

in js, developers prefix name with `_` to indicate internal data or methods

## Class Keyword

use `class` to rewrite "class" code

    function User(name) {
        this.name = name;
    }

    User.prototype.sayHi = function() {
        alert(this.name);
    }

    let user = new User("John");
    user.sayHi();

    class User {

        constructor(name) {
            this.name = name;
        }

        sayHi() {
            alert(this.name);
        }

        // getter and setter are allowed
    }

    let user = new User("John");
    user.sayHi();

but there are some differences. the `constructor` method must be called with
`new`. when there is no constructor, an empty one will be created automatically.
classes always use `strict` mode.

everything inside `class` goes to prototype. thus they have to be methods. no
class data could be declared insdie. if really needed, they must be manually
handled outside `class`.

Just like functions, classes can be defined inside another expression, passed
around, returned etc.

static methods are bound to the class function, not its prototype

    class User {
        static staticMethod() {
            alert(this == User);
        }
    }

    User.staticMethod(); // true

    let user = new User();
    user.staticMethod(); // false, does not exist

class inheritance. but any expression evaluates to a class can be used after
`extends`

    class Rabbit extends Animal {
        constructor() {
            // super(...) - call a parent constructor
            // must call the inherited constructor first,
            // otherwise this won't be bound
        }
        stop() {
            // super.method(...) - call a parent method.
        }
    }

check instance against class

    obj instanceof Class

## Error Handling

    try {
    } catch(err) {
    } // finally

built-in error objects have 3 main properties

    name - Error name. For an undefined variable that’s "ReferenceError".
    message - Textual message about error details.
    stack - Current call stack: a string with information about the sequence of
            nested calls that led to the error. Used for debugging purposes.

throw errors

    throw <error object> // new Error(message)

    class Error {
        constructor(message) {
            this.message = message;
            this.name = "Error"; // (different names for different built-in error classes)
            this.stack = <nested calls>; // non-standard, but most environments support it
        }
    }

    class ValidationError extends Error {
        constructor(message) {
            super(message); // (1)
            this.name = "ValidationError"; // (2)
        }
    }

    throw new ValidationError("Whoops!");

## Browser Environment

`window` root object in browser environment. `document` object gives access to
the page content

    // change the background color to red
    document.body.style.background = 'red';
    // change it back after 1 second
    setTimeout(() => document.body.style.background = '', 1000);

DOM, Document Object Model, [WhatWG DOM Standards][]

Browser Object Model (BOM) are additional objects provided by the browser (host
environment) to work with everything except the document. `navigator` object
provides background information about the browser and the operation
system. There are many properties, but two most widely known are:
`navigator.userAgent` – about the current browser, and `navigator.platform` –
about the platform (can help to differ between Windows/Linux/Mac etc).
`location` object allows to read the current URL and redirect the browser to a
new one.

    if (confirm("Go to wikipedia?")) {// redirect the browser to another URL
        location.href = 'https://wikipedia.org';
    }

Functions `alert/confirm/prompt` are also a part of BOM

## DOM Tree

The backbone of an HTML document is tags. According to Document Object Model
(DOM), every HTML-tag is an object. Nested tags are called “children” of the
enclosing one. The text inside a tag it is an object as well. All these objects
are accessible using JavaScript.

browser will auto-correct malformed html when creating dom tree, like insert a
`tbody` to `table`.

interact with dom. use inpsect in chrome and press `Esc` to open the console.
`$0` refers the last dom object, `$1` refers to previously selected dom object.

    $0.style.background = "red";
    document.body;

hierarchy

    <html> = document.documentElement - topmost element
    <body> = document.body
    <head> = document.head

The `childNodes` collection (array-like object supporting index access) provides
access to all child nodes, including text nodes.  Properties `firstChild` and
`lastChild` give fast access to the first and last children.

    for (let i = 0; i < document.body.childNodes.length; i++) {
        alert( document.body.childNodes[i] ); // Text, DIV, Text, UL, ..., SCRIPT
    }
    document.body.firstChild
    document.body.lastChild
    elem.hasChildNodes() - check whether there are any child nodes.

`childNodes` is also iterable supporting `for..of`. but it does not support
array methods, because it is not a real array. but this problem is easy to
fix. just use `Array.from()`. another thing is that do not use `for..in` to
iterate over collection. it will list all enumerable properties including some
extra properties.

parent and siblings

    document.body.parentNode === document.documentElement
    document.head.nextSibling === document.body
    document.body.previousSibling === document.head

ways to only include tag elements

    children – only those children that are element nodes.
    firstElementChild, lastElementChild – first and last element children.
    previousElementSibling, nextElementSibling – neighbour elements.
    parentElement – parent element.

Some types of DOM elements, e.g. tables, provide additional properties and
collections to access their content.

## DOM Selection

methods to select elements

    let elem = document.getElementById(id-name)
        If an element has the `id` attribute, then there’s a global variable by
        the name from that `id`.

    elem.getElementsByTagName(tag)
        looks for elements with the given tag and returns the collection of
        them. The tag parameter can also be a star "*" for “any tags”.

    // these two are rarely used
    elem.getElementsByClassName(className)
        returns elements that have the given CSS class. Elements may have other
        classes too.
    document.getElementsByName(name)
        returns elements with the given name attribute, document-wide. Exists
        for historical reasons, very rarely used, we mention it here only for
        completeness.

    let elements = elem.querySelectorAll('ul > li:last-child');
        returns all elements inside elem matching the given CSS selector. That’s
        the most often used and powerful method. any valid CSS selector could be used

    elem.querySelector(css)
        returns the first element for the given CSS selector. In other words,
        the result is the same as elem.querySelectorAll(css)[0], but the latter
        is looking for all elements and picking one, while elem.querySelector
        just looks for one. So it’s faster and shorter to write.

    elem.matches(css)
        does not look for anything, it merely checks if elem matches the given
        CSS-selector. It returns true or false. useful to filter elements of
        interest when iterating through a selected collection of elements

    elem.closest(css)
        looks the nearest ancestor that matches the CSS-selector. The elem
        itself is also included in the search.

    elemA.contains(elemB)
        returns true if elemB is inside elemA (a descendant of elemA) or when
        elemA==elemB.

## DOM Node

hierarchy of dom classes

![hierarchy o fdome](https://javascript.info/article/basic-dom-node-properties/dom-class-hierarchy@2x.png)

ways to check class of a dom element

    elem.constructor.name
    alert(elem) // elem.toString()
    elem instanceof HTMLBodyElement

Most browsers support two commands in their developer tools: `console.log` and
`console.dir.` They output their arguments to the console. For JavaScript
objects these commands usually do the same. But for DOM elements they are
different:

    console.log(elem) shows the element DOM tree.
    console.dir(elem) shows the element as a DOM object, good to explore its properties.

    elem.nodeType - uses an old-fashioned integer to indicate the “type” of a DOM node.
    elem.nodeName - gets elem tag name, exists for any node
    elem.tagName - gets elem tag name, only exists for Element node

    elem.innerHTML
        gets or sets the HTML inside the element as a string. script doesn't get
        executed. only for Element node
    elem.outerHTML
        contains the full HTML of the element. That’s like innerHTML plus the element itself.
    elem.textContent
        provides access to the text inside the element: only text, minus all <tags>.

    node.nodeValude / node.data - innerHTML for any node

    elem.hidden - specifies whether the element is visible or not.
    <div id="elem">A blinking element</div>
    <script> // blink
        setInterval(() => elem.hidden = !elem.hidden, 1000);
    </script>

    elem.value – the value for <input>, <select> and <textarea> (HTMLInputElement, HTMLSelectElement…).
    elem.href – the “href” for <a href="..."> (HTMLAnchorElement).
    elem.id – the value of “id” attribute, for all elements (HTMLElement).

to explore properties for an element

* use `console.dir()`
* go spec <https://html.spec.whatwg.org/#htmlinputelement>

## HTML tag attributes and DOM node object

dom objects are regular js objects. we can add properties and methods on the
fly, even to dom prototypes

    Element.prototype.sayHi = function() {
    alert(`Hello, I'm ${this.tagName}`);
    };
    document.documentElement.sayHi(); // Hello, I'm HTML
    document.body.sayHi(); // Hello, I'm BODY

standard attributes of HTML tags are converted automatically to DOM properties.
non-standard attributes are not converted. but can be accessed by

    elem.hasAttribute(name) – checks for existence.
    elem.getAttribute(name) – gets the value.
    elem.setAttribute(name, value) – sets the value.
    elem.removeAttribute(name) – removes the attribute.
    elem.attributes
        a collection of objects that belong to a built-in Attr class, with name
        and value properties.
    for (let attr of elem.attributes) {
        alert( attr.name + " = " + attr.value );
    }

changes to attributes are automatically updated to properties. but changes to
properties are not updated to attributes

attributes start with `data-` are reserved for programmers' use. custom
attributes should follow this naming convention.

## Modify DOM

create element

    document.createElement(tag) - Creates a new element with the given tag
    document.createTextNode(text) - Creates a new text node with the given text

insertion

    parentElem.appendChild(node)
        Appends node as the last child of parentElem
    parentElem.insertBefore(node, nextSibling)
        Inserts node before nextSibling into parentElem.
    parentElem.replaceChild(node, oldChild)
        Replaces oldChild with node among children of parentElem.

    <ol id="list">
        <li>0</li>
        <li>1</li>
        <li>2</li>
    </ol>

    <script>
        let newLi = document.createElement('li');
        newLi.innerHTML = 'Hello, world!';
        list.appendChild(newLi);
    </script>

but above methods are old-school. we have two other sets methods

    node.append(...nodes or strings) – append nodes or strings at the end of node,
    node.prepend(...nodes or strings) – insert nodes or strings into the beginning of node,
    node.before(...nodes or strings) –- insert nodes or strings before the node,
    node.after(...nodes or strings) –- insert nodes or strings after the node,
    node.replaceWith(...nodes or strings) –- replaces node with the given nodes or strings.
    // works with both node and string, and could handle multiple things in one call

    <ol id="ol">
        <li>0</li>
        <li>1</li>
        <li>2</li>
    </ol>

    <script>
        ol.before('before'); // before ol, text
        ol.after('after');   / after ol, text

        let prepend = document.createElement('li');
        prepend.innerHTML = 'prepend';
        ol.prepend(prepend); // in ol, node

        let append = document.createElement('li');
        append.innerHTML = 'append';
        ol.append(append); // in to, node
    </script>

another set. but most of time only `insertAdjacentHTML` is used. because for
other two functions, above methods are shorter

    elem.insertAdjacentHTML(where, html) // insert html in string
    elem.insertAdjacentText(where, text)
        the same syntax, but a string of text in inserted “as text” instead of HTML,
    elem.insertAdjacentElement(where, elem)
        the same syntax, but inserts an element.

    where must be a string of
    "beforebegin" – insert html before elem,
    "afterbegin" – insert html into elem, at the beginning,
    "beforeend" – insert html into elem, at the end,
    "afterend" – insert html after elem.

clone node to be inserted more than once

    elem.cloneNode(true) // deep copy
    elem.cloneNode(false) // shallow copy without child elements

remove

    parentElem.removeChild(node) Removes elem from parentElem (assuming it’s a child).
    node.remove() Removes the node from its place.

## Style and Class (CSS)

    elem.className - css class name, multiple classes as a whole string
    elem.classList - list of classes, iterable for for..of

    elem.classList.add/remove("class") – adds/removes the class.
    elem.classList.toggle("class") – if the class exists, then removes it, otherwise adds it.
    elem.classList.contains("class") – returns true/false, checks for the given class.

    elem.style - corresponds to the style attribute
    elem.style.width="100px" - style="width:100px"
    background-color  => elem.style.backgroundColor
    z-index           => elem.style.zIndex
    border-left-width => elem.style.borderLeftWidth

    browser prefixed properties like -moz-border-radius, -webkit-border-radius
    become uppercases
    button.style.MozBorderRadius = '5px';
    button.style.WebkitBorderRadius = '5px';

`elem.style` only works with style attributes not computed style

    getComputedStyle(element, pseudo)
        element - Element to read the value for.
        pseudo - A pseudo-element if required, for instance ::before. An empty
                 string or no argument mean the element itself.
    <head>
        <style> body { color: red; margin: 5px } </style>
    </head>
    <body>
        <script>
            let computedStyle = getComputedStyle(document.body);
            alert( computedStyle.marginTop ); // 5px
            alert( computedStyle.color ); // rgb(255, 0, 0)
        </script>
    </body>

## Size and Positions

![element size](http://javascript.info/article/size-and-scroll/metric-css@2x.png)

![geometry](http://javascript.info/article/size-and-scroll/metric-all@2x.png)

<http://javascript.info/size-and-scroll>








[Google's Style Guide]: https://google.github.io/styleguide/javascriptguide.xml

[Mocha]: http://mochajs.org/
[Chai]: http://chaijs.com/
[Sinon]: http://sinonjs.org/

[propose]: https://tc39.github.io/ecma262/
[specification]: http://www.ecma-international.org/publications/standards/Ecma-262.htm
[support status]: https://kangax.github.io/compat-table/es6/

[Babel]: https://babeljs.io/

[lodash]: https://lodash.com/

[WhatWG DOM Standards]: https://dom.spec.whatwg.org/
