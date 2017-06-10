# MacPorts

Sync Portfiles

    port selfupdate

see outdated

    port installed outdated

update outdated ports

    port upgrade outdated

see inactive

    port installed inactive

uninstall inactive

    port uninstall inactive

list installed ports

    port installed

see info or vairants

    port info emacs
    port variants emacs

installed from source. otherwise macports will try to install from binary,
make it impossible to use local patch

    port -s install emacs

select list

    port select --summary

select set

    port select --set python python27

patch emacs to support 24bit. The patch has to be put under the `files`
directory of macports. remember to force source install

    # Portfile
    # /opt/local/var/macports/sources/rsync.macports.org/macports/release/tarballs/ports/
    patchfiles emacs.patch
