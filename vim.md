# Vimdiff

It's very neat when you want to do a partial checkout of a single file or merge
conficting files.

    $ vimdiff f1 f2

    ]c :        - next difference
    [c :        - previous difference
    do          - diff obtain
    dp          - diff put
    zo          - open folded text
    zc          - close folded text
    :diffupdate - re-scan the files for differences]

# Vim

do not use any rc file

    vim -u NONE
