package com.dstarlab.dstarcodegeneration

import javax.lang.model.element.Element

abstract class Generator {
    abstract fun prepareClassInitialization(element: Element)
}