# MacPorts

Sync Portfiles

    port selfupdate

update outdated ports

    port upgrade outdated

uninstall inactive

    port uninstall inactive

list installed ports

    port installed

see info or vairants

    port info emacs
    port variants emacs

installed from source, otherwise macports will try to install from binary

    port -s install emacs

select list

    port select --summary

select set

    port select --set python python27

patch emacs to support 24bit. The patch has to be put under the `files`
directory of macports. remember to force source install

    # Portfile
    patchfiles emacs.patch
