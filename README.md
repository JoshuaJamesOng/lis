# Local Image Server/Spike
Spike into serving images using a local web server

# Implementation
* App is given raw HTML
* DOM is traversed for `<img>` tags
* `src` is modified to point to local server e.g. `http://127.0.0.1:8080/images/?src=[ORIGINAL_SRC]`
* Transformed HTML is displayed in `WebView`
* `WebView` shows static data and fires request for `img`s
* Local server parses request parameters and returns the image

# Advantages
* Page is not blocked from use whilst images are being loaded or downloaded 
* Requests wait until image is available - negating need to callback to view when image available and/or re-render whole view.

# Thoughts
* Placeholder image could be achieved using `background` on CSS style for all images
