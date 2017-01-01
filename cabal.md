# Caveat Emptor

By default, cabal installs to user's directory. This behavior can be toggle
explicitly with `--user` or `--global` option. And this behavior must be
consistend with

    runhaskell Setup.hs configure

But unfortunately, this command defaults to use `--global` option implicitly.
Thus only following two combos work

    cabal install [--user] foo
    runhaskell Setup.hs configure --user

    sudo cabal install --global foo
    runhaskell Setup.hs configure [--global]

# Update Before First Time Use

cabal needs to pull down the package list. This can only be stored to user's
directory `~/.cabal/`

    cabal update


# List Instead of Search..

cabal calls it list... Perhaps it refers to `ls |grep `

    cabal list foo
    cabal list --installed
    ghc-pkg list [--user|--global]

# Uninstall

    ghc-pkg unregister [--user|--global] [--force] foo

There is also a command called `cabal-uninstall`. Install and use it in this
way

    cabal-uninstall [--user|--global] foo
