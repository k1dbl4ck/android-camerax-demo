package nl.venters.cameraxdemo

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import nl.venters.cameraxdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cameraHelper: CameraHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        cameraHelper = CameraHelper(
            owner = this,
            context = this.applicationContext,
            viewFinder = binding.cameraView,
            onResult = ::onResult
        )

        cameraHelper.start()
    }

    private fun onResult(result: String) {
        Log.d(TAG, "Result is $result")
        binding.textResult.text = result
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        cameraHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        const val TAG = "CameraXDemo"
    }
}