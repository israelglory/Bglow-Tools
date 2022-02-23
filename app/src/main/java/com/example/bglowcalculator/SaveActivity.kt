package com.example.bglowcalculator

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bglowcalculator.databinding.ActivitySaveBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class SaveActivity : AppCompatActivity() {
    private var pd: ProgressDialog? = null
    private lateinit var binding: ActivitySaveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pd = ProgressDialog(this)
        binding.preview.setOnClickListener {
            previewInput(it)
        }

    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun previewInput(view: View?) {
        val dET1 = binding.dET1
        val d1 = binding.d1
        d1.text = dET1.text


        val rateET1 = binding.rateET1
        val r1 = binding.r1
        r1.text = rateET1.text


        val AET1 = binding.AET1
        val A1 = binding.A1
        A1.text = AET1.text

        val dET2 = binding.dET2
        val d2 = binding.d2
        d2.text = dET2.text

        val rateET2 = binding.rateET2
        val r2 = binding.r2
        r2.text = rateET2.text

        val AET2 = binding.AET2
        val A2 = binding.A2
        A2.text = AET2.text

        val dET3 = binding.dET3
        val d3 = binding.d3
        d3.text = dET3.text

        val rateET3 = binding.rateET3
        val r3 = binding.r3
        r3.text = rateET3.text

        val AET3 = binding.AET3
        val A3 = binding.A3
        A3.text = AET3.text

        val dET4 = binding.dET4
        val d4 = binding.d4
        d4.text = dET4.text

        val rateET4 = binding.rateET4
        val r4 = binding.r4
        r4.text = rateET4.text

        val AET4 = binding.AET4
        val A4 = binding.A4
        A4.text = AET4.text

        val dET5 = binding.dET5
        val d5 = binding.d5
        d5.text = dET5.text

        val rateET5 = binding.rateET5
        val r5 = binding.r5
        r5.text = rateET5.text

        val AET5 = binding.AET5
        val A5 = binding.A5
        A5.text = AET5.text

        val CnameET = binding.CnameEt
        val Cname = binding.Cname
        Cname.text = CnameET.text

        val addressET = binding.CaddressET
        val address = binding.CustomerHadress
        address.text = addressET.text

        val numberET = binding.CphoneEt
        val number = binding.Customerdigit
        number.text = numberET.text

        val collectET = binding.collectionET
        val collection = binding.collection
        val collect = collectET.text
        collection.text = "Collection Date: $collect"

        val AA1 = binding.A1.text.toString()
        val AA2 = binding.A2.text.toString()
        val AA3 = binding.A3.text.toString()
        val AA4 = binding.A4.text.toString()
        val AA5 = binding.A5.text.toString()

        val amoumt1 = AA1.toInt()
        val amoumt2 = AA2.toInt()
        val amoumt3 = AA3.toInt()
        val amoumt4 = AA4.toInt()
        val amoumt5 = AA5.toInt()


        val Pretotal = amoumt1 + amoumt2 + amoumt3 + amoumt4 + amoumt5
        val NGN = Locale("en","NG")
        val formattedTotal = NumberFormat.getCurrencyInstance(NGN).format(Pretotal)
        binding.RecieptAmount.text = getString(R.string.Final_Reciept_Amount, formattedTotal)



        val date = binding.DateText
        val simpledate = SimpleDateFormat("dd/MM/yy")
        val CurrentDate :String = simpledate.format(Date())
        date.text= "Issued Date: $CurrentDate"
        date.visibility= View.VISIBLE

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
        Toast.makeText(applicationContext, "Previewed(Scroll-up)", Toast.LENGTH_LONG).show()

    }


    fun SaveClick(view: View?) {
        pd!!.setMessage("saving your image")
        pd!!.show()
        val savingLayout = binding.idForSaving
        val file = saveBitMap(this, savingLayout)!!
        pd!!.cancel()
        Log.i("TAG", "Drawing saved to the gallery!")
        Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()
    }

    private fun saveBitMap(context: Context, drawView: View): File? {
        val pictureFileDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            "Bglow"
        )
        if (!pictureFileDir.exists()) {
            val isDirectoryCreated = pictureFileDir.mkdirs()
            if (!isDirectoryCreated) Log.i("TAG", "Can't create directory to save the image")
            return null
        }

        val filename = pictureFileDir.path + File.separator + System.currentTimeMillis() + ".jpg"
        val pictureFile = File(filename)
        val bitmap: Bitmap = getBitmapFromView(drawView)!!
        try {
            pictureFile.createNewFile()
            val oStream = FileOutputStream(pictureFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, oStream)
            oStream.flush()
            oStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            Log.i("TAG", "There was an issue saving the image.")
        }
        scanGallery(context, pictureFile.absolutePath)
        return pictureFile
    }

    //create bitmap from view and returns it
    private fun getBitmapFromView(view: View): Bitmap? {
        //Define a bitmap with the same size as the view
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        //Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        //Get the view's background
        val bgDrawable = view.background
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas)
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        }
        // draw the view on the canvas
        view.draw(canvas)
        //return the bitmap
        return returnedBitmap
    }

    // used for scanning gallery
    private fun scanGallery(cntx: Context, path: String) {
        try {
            MediaScannerConnection.scanFile(
                cntx, arrayOf(path), null
            ) { path, uri -> }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("TAG", "There was an issue scanning gallery.")
        }
    }


}