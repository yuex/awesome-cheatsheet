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
    }

function declarations. two form

    // function statement
    function foo() {
    }

    // function expression, could be used to define local function
    var bar = function() {
    };
