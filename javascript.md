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
like [lodash][]'s `_.cloneDeep(obj)` as in [](https://lodash.com/docs#cloneDeep)


[Google's Style Guide]: https://google.github.io/styleguide/javascriptguide.xml

[Mocha]: http://mochajs.org/
[Chai]: http://chaijs.com/
[Sinon]: http://sinonjs.org/

[propose]: https://tc39.github.io/ecma262/
[specification]: http://www.ecma-international.org/publications/standards/Ecma-262.htm
[support status]: https://kangax.github.io/compat-table/es6/

[Babel]: https://babeljs.io/

[lodash]: https://lodash.com/
