package dev.marcos.podcast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.imageview.ShapeableImageView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PlayerFragment : Fragment() {

    data class Point(var x: Float, var y: Float)

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var miniPlayerButtonPlayPause: ShapeableImageView
    private lateinit var miniPlayerImageViewCover: ShapeableImageView
    private lateinit var miniPlayerTextViewTitle: TextView
    private lateinit var miniPlayerTextViewSubtitle: TextView
    private lateinit var miniPlayerDescription: View
    private lateinit var imageViewCover: ShapeableImageView
    private lateinit var textViewTitle: TextView
    private lateinit var textViewSubtitle: TextView
    private lateinit var description: View

    private val miniPlayerTextViewTitlePoint = Point(0.0f, 0.0f)
    private val miniPlayerTextViewSubtitlePoint = Point(0.0f, 0.0f)
    private val miniPlayerDescriptionAreaPoint = Point(0.0f, 0.0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        miniPlayerButtonPlayPause = view.findViewById(R.id.button_play_pause_inner2)
        miniPlayerImageViewCover = view.findViewById(R.id.image_view_cover_podcast_inner2)
        miniPlayerTextViewTitle = view.findViewById(R.id.text_view_title_inner2)
        miniPlayerTextViewSubtitle = view.findViewById(R.id.text_view_subtitle_inner2)
        miniPlayerDescription = view.findViewById(R.id.description_area_inner2)
        imageViewCover = view.findViewById(R.id.image_view_cover_podcast)
        textViewTitle = view.findViewById(R.id.text_view_title)
        textViewSubtitle = view.findViewById(R.id.text_view_subtitle)
        description = view.findViewById(R.id.description_area)

    }

    fun animate(offset: Float) {

        with(miniPlayerTextViewTitlePoint) {
            if (x == 0.0f || y == 0.0f) {
                x = miniPlayerTextViewTitle.x
                y = miniPlayerTextViewTitle.y
            }
        }

        with(miniPlayerTextViewSubtitlePoint) {
            if (x == 0.0f || y == 0.0f) {
                x = miniPlayerTextViewSubtitle.x
                y = miniPlayerTextViewSubtitle.y
            }
        }

        with(miniPlayerDescriptionAreaPoint) {
            if (x == 0.0f || y == 0.0f) {
                x = miniPlayerDescription.x
                y = miniPlayerDescription.y
            }
        }

        with(miniPlayerButtonPlayPause) {
            alpha = 1 - (offset * 2.0f)
        }

        with(miniPlayerImageViewCover) {
            pivotX = 0.0f
            pivotY = 0.0f
            scaleX = 1.0f + (imageViewCover.measuredWidth - measuredWidth.toFloat()) / measuredWidth * offset
            scaleY = 1.0f + (imageViewCover.measuredHeight - measuredHeight.toFloat()) / measuredHeight * offset
            translationX = imageViewCover.x * offset
            translationY = imageViewCover.y * offset
        }

        val margin = resources.getDimension(R.dimen.default_margin)

        with(miniPlayerTextViewTitle) {
            val point = miniPlayerTextViewTitlePoint
            x = point.x + (textViewTitle.x - point.x + margin) * offset
            y = point.y + (textViewTitle.y - point.y + margin) * offset
        }

        with(miniPlayerTextViewSubtitle) {
            val point = miniPlayerTextViewSubtitlePoint
            x = point.x + (textViewSubtitle.x - point.x + margin) * offset
            y = point.y + (textViewSubtitle.y - point.y + margin) * offset
        }

        with(miniPlayerDescription) {
            val point = miniPlayerDescriptionAreaPoint
            x = point.x + (description.x - point.x + margin) * offset
            y = point.y + (description.y - point.y + margin) * offset
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}