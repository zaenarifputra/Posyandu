package model

import android.graphics.drawable.Drawable

class ISOnBoardingModel {
    var imageSource: Drawable? = null
    var isActive: Boolean
    var title: String
    var description: String? = null

    constructor(imageSource: Drawable?, isActive: Boolean, title: String) {
        this.imageSource = imageSource
        this.isActive = isActive
        this.title = title
    }

    constructor(imageSource: Drawable?, isActive: Boolean, title: String, description: String?) {
        this.imageSource = imageSource
        this.isActive = isActive
        this.title = title
        this.description = description
    }
}