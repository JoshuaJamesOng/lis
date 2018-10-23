package com.ongtonnesoup.localimageserver

import android.util.Base64

class UiPageMapper {

    fun map(page: Page): UiPage {
        return UiPage(
            base64EncodedHtml = Base64.encodeToString(page.html.toByteArray(), Base64.NO_PADDING)
        )
    }

}