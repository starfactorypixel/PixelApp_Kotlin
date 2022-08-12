package ru.startfactory.core.di

import org.kodein.di.DirectDIAware
import org.kodein.di.instance

/**
 * Just short alias for instance()
 */
inline fun <reified T : Any> DirectDIAware.i(tag: Any? = null): T = instance(tag)