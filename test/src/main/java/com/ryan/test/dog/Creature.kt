package com.ryan.test.dog

import javax.inject.Inject

abstract class Creature

class Dog constructor() : Creature()


class Pig @Inject constructor() : Creature()
