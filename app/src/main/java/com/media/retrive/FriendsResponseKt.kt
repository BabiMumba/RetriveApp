package com.media.retrive

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class FriendsResponseKt {
    var name: String? = null
    var title: String? = null
    var company: String? = null
    var image: String? = null

    constructor() {}
    constructor(name: String?, title: String?, company: String?, image: String?) {
        this.name = name
        this.title = title
        this.company = company
        this.image = image
    }
}