# apartial

A slightly more advanced partial

## Usage

The regular clojure core partial  function will only let you partial initialize a function with the arguments from left to right. There is no
way to skip a parameter in the middle. The apartial macro will let you do that by using _ (underscore) for the uninitialized arguments.
An example

```clojure
=> (use 'apartial.core)
=> (defn add-three-numbers [x0 x1 x2] (+ x0 x1 x2))
;initialize x0 and x2 with apartial
=> (def ap (apartial add-three-numbers 1 _ 3))
=> (ap 2)
6
=> (ap 4)
8

```

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
