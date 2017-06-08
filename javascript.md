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

array operations

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

    !function() {
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
