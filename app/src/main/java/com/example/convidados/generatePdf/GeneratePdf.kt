package com.example.convidados.generatePdf

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import com.example.convidados.Model.GuestModel
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.ceil
import kotlin.math.round


@Suppress("UNREACHABLE_CODE")
class GeneratePdf {

    @SuppressLint("SimpleDateFormat")
    fun createPdf(list: List<GuestModel>?): Boolean {

        val numPagina = ceil(list!!.size/35.00)
        var numChunkedPerIndex = 0
        val document = PdfDocument()
        val chunks: List<List<GuestModel>> = list.chunked(35)


        for (i in 1..numPagina.toInt()){

            val page = document.startPage(
                PdfDocument.PageInfo.Builder(
                    595,
                    842,
                    1
                ).create()
            )

            val canvas = page.canvas
            val paint = Paint()
            paint.color = Color.BLACK
            paint.textSize = 12f

            var yPosition = 50f
            val xPosition = 50f

            for (j in 0 until chunks[numChunkedPerIndex].size) {
                val guest: String = chunks[numChunkedPerIndex][j].name

                canvas.drawText(guest, xPosition, yPosition, paint)

                yPosition += paint.textSize + 10
            }
            numChunkedPerIndex += 1
            document.finishPage(page)
        }

        val directoryPath = Environment.getExternalStorageDirectory().toString() + "/Documents/"
        val file = File(directoryPath)
        if (!file.exists()) {
            file.mkdirs()
        }

        val dateTime: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = Date()
        val dateFormated = dateTime.format(date)

        val filePath = File(directoryPath, "lista_de_Convidados_${dateFormated}.pdf")

     return   try {
            val fileOutputStream = FileOutputStream(filePath)
            document.writeTo(fileOutputStream)
            document.close()
            fileOutputStream.close()
            return true
        } catch (e: Exception) {
            Log.i(TAG, "createPdf: Erro ao salvar o arquivo PDF")
         return false
        }


    }

}