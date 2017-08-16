# Golang

Currently, local struct are allocated on heap so that you can safely return
pointers to local struct from a function.

The order that `range` is iterated is undefined, which may even differ from
time to time.

    package main

    import "fmt"

    func main() {
        fmt.Println("hello world")
    }
