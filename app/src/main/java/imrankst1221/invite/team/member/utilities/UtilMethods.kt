package imrankst1221.invite.team.member.utilities

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.graphics.Bitmap
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import java.io.IOException
import java.io.InputStream
import java.lang.IllegalArgumentException

object UtilMethods {
    val TAG = "---UtilMethods"

    /**
     * read file form asset
     * @context is application context
     * @fileName is the file name
     */
    fun loadJSONFromAsset(context: Context, fileName: String): String? {
        var json: String? = null
        json = try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    /**
     * short toast
     * @context is application context
     * @message is the show message
     */
    fun showLongToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    /**
     * long toast
     * @context is application context
     * @message is the show message
     */
    fun showShortToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Copy Into ClipBoard
     * @context is application context
     * @message is the message to copy
     */
    fun copyIntoClipBoard(context: Context, message: String) {
        val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(
            "Share URL",
            message
        )
        clipboardManager.setPrimaryClip(clipData)
    }


    /**
     * Encode As Bitmap
     * @context is application context
     * @str is the encode bitmap
     */
    @Throws(WriterException::class)
    fun encodeAsBitmap(str: String?): Bitmap? {
        val WHITE = -0x1
        val BLACK = -0x1000000
        val WIDTH = 1000
        val HEIGHT = 1000
        val result: BitMatrix = try {
            MultiFormatWriter().encode(
                str,
                BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null
            )
        } catch (iae: IllegalArgumentException) {
            Logger.e(TAG, iae.message.toString())
            return null
        }
        val w = result.width
        val h = result.height
        val pixels = IntArray(w * h)
        for (y in 0 until h) {
            val offset = y * w
            for (x in 0 until w) {
                pixels[offset + x] = if (result[x, y]) BLACK else WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h)
        return bitmap
    }


}