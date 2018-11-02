package com.example.nvolkov.myapplication2

import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.nvolkov.myapplication2.PoolManager.openIndyPool
import com.sun.jna.NativeLibrary

import kotlinx.android.synthetic.main.activity_main.*
import libcore.io.Libcore
import org.hyperledger.indy.sdk.LibIndy
import org.hyperledger.indy.sdk.pool.Pool
import org.hyperledger.indy.sdk.pool.PoolJSONParameters
import org.hyperledger.indy.sdk.pool.PoolLedgerConfigExistsException
import org.hyperledger.indy.sdk.wallet.Wallet
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ExecutionException

class MainActivity : AppCompatActivity() {

    var CREDENTIALS = "{\"key\": \"key\"}"
    val walletConfig = """
        {
            "id": "idlala",
            "storageType": "default"
        }
    """.trimIndent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            println("CLICKED")
            val walletName = "default-wallet"
            val poolName = "default-pool"
            val credentials = """{"key": "key"}"""
//            val issuerWalletConfig = SerializationUtils.anyToJSON(WalletConfig("issuerWallet"))

//            val hm = File("/data/data/com.example.nvolkov.myapplication2/libindy.so")

//            System.setProperty("jna.library.path", "/data/data/com.example.nvolkov.myapplication2")
            System.setProperty("jna.debug_load", "true")
            System.setProperty("rust.log", "trace")
            System.setProperty("rust_log", "trace")
            System.setProperty("RUST_LOG", "trace")


            Libcore.os.setenv("RUST_LOG", "debug", true)
            println(Libcore.os.getenv("RUST_LOG"))

            println("RUST LOG: ${System.getenv("RUST_LOG")} ${System.getenv("rust.log")} ${System.getenv("rust_log")}")
            println(System.getenv())


//            NativeLibrary.addSearchPath("libindy", "/data/data/com.example.nvolkov.myapplication2")

            LibIndy.init()

            val pool = openIndyPool(this, poolName)

//            println(pool.poolHandle)


//            Wallet.createWallet(walletConfig, CREDENTIALS).get()
//            issuerWallet = Wallet.openWallet(issuerWalletConfig, CREDENTIALS).get()

//            val walletConfig = SerializationUtils.anyToJSON(WalletConfig(walletName))
//            poolName = PoolManager.DEFAULT_POOL_NAME
//            val pool = PoolManager.openIndyPool(PoolManager.defaultGenesisResource, poolName)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}
