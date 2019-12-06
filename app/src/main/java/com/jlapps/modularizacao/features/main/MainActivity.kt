package com.jlapps.modularizacao.features.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.jlapps.modularizacao.R
import com.jlapps.modularizacao.extensions.visible
import com.jlapps.modularizacao.features.listProducts.ProductListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var splitInstallManager: SplitInstallManager
    lateinit var request: SplitInstallRequest
    val DYNAMIC_FEATURE = "news_feature"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDynamicModules()

        setClickListeners()

    }

    private fun initDynamicModules() {
        splitInstallManager = SplitInstallManagerFactory.create(this)
        request = SplitInstallRequest
            .newBuilder()
            .addModule(DYNAMIC_FEATURE)
            .build()
    }

    private fun setClickListeners() {

        btProducts.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProductListActivity::class.java))
        }

        btDownloadNews.setOnClickListener {
            if (!isDynamicFeatureDownloaded(DYNAMIC_FEATURE)) {
                downloadFeature()
            } else {
                buttonDeleteNewsModule.visible(true)
                btOpenNewsModule.visible(true)
                btDownloadNews.visible(false)
            }
        }

        btOpenNewsModule.setOnClickListener {
            val intent = Intent().setClassName(this, "br.com.heiderlopes.news_feature.NewsListActivity")
            startActivity(intent)
        }

        buttonDeleteNewsModule.setOnClickListener {
            val list = ArrayList<String>()
            list.add(DYNAMIC_FEATURE)
            uninstallDynamicFeature(list)
        }
    }

    private fun uninstallDynamicFeature(list: List<String>) {
        splitInstallManager.deferredUninstall(list)
            .addOnSuccessListener {
                buttonDeleteNewsModule.visible(false)
                btOpenNewsModule.visible(false)
                btDownloadNews.visible(true)
            }
    }

    private fun isDynamicFeatureDownloaded(feature: String): Boolean =
        splitInstallManager.installedModules.contains(feature)

    private fun downloadFeature() {
        splitInstallManager.startInstall(request)
            .addOnFailureListener {
            }
            .addOnSuccessListener {
                btOpenNewsModule.visible(true)
                buttonDeleteNewsModule.visible(true)
                btDownloadNews.visible(false)
            }
            .addOnCompleteListener {
            }
    }

    //Check status download dynamic module
    /*var mySessionId = 0
    val listener = SplitInstallStateUpdatedListener {
        mySessionId = it.sessionId()
        when (it.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                val totalBytes = it.totalBytesToDownload()
                val progress = it.bytesDownloaded()
                // Update progress bar.
            }
            SplitInstallSessionStatus.INSTALLING -> Log.d("Status", "INSTALLING")
            SplitInstallSessionStatus.INSTALLED -> Log.d("Status", "INSTALLED")
            SplitInstallSessionStatus.FAILED -> Log.d("Status", "FAILED")
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> Log.d("Status", "REQUIRES_USER_CONFIRMATION")
        }
    }*/
}


