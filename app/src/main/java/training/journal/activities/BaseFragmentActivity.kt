package training.journal.activities

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.view_appbar.*
import training.journal.R
import training.journal.fragments.BaseFragment
import training.journal.lifecycle.Page
import training.journal.lifecycle.Page.Companion.PAGE_KEY
import training.journal.utils.logger.Logger
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

open class BaseFragmentActivity : AppbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment: Page.Fragment? = intent.getSerializableExtra(PAGE_KEY) as? Page.Fragment

        fragment?.let {
            setSupportingFragment(it.clazz)
        }
    }

    private fun setSupportingFragment(clazz: KClass<out BaseFragment>) {
        val supportingFragment = clazz.createInstance()
        Logger.d(this, "Set fragment ${supportingFragment::class.simpleName}")
        supportFragmentManager.beginTransaction().add(R.id.main_container, supportingFragment).commit()
        supportFragmentManager.executePendingTransactions()
    }

    fun setFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_container, fragment)
        ft.addToBackStack(null)
        ft.setCustomAnimations(
                android.R.animator.fade_in, android.R.animator.fade_out)
        ft.commit()
    }

    fun setPrevFragment() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        } else {
            Logger.e(this, "Fragment stack is empty!")
        }
    }

    override fun getActivityLayoutId() = R.layout.activity_base_fragment

    override fun getToolbarView(): Toolbar = base_toolbar
}
