package beer.hucet.com.beer.glide;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by taesu on 2017-11-21.
 */

@GlideExtension
public class GlideThumbnailExtension {

    private GlideThumbnailExtension() {
    }

    private static final RequestOptions REQUEST_OPTIONS_THUMBNAIL =
            RequestOptions.overrideOf(50);

    @GlideType(Drawable.class)
    public static void asThumbnail(RequestBuilder<Drawable> requestBuilder) {
        requestBuilder
                .apply(REQUEST_OPTIONS_THUMBNAIL)
                .thumbnail(0.25f);
    }
}