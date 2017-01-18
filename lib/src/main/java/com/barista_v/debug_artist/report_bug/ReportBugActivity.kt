package com.barista_v.debug_artist.report_bug

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.EditText
import android.widget.Toast
import com.barista_v.debug_artist.R
import com.barista_v.debug_artist.repositories.BugReportRepository
import java.lang.ref.WeakReference

class ReportBugActivity : AppCompatActivity(), ReportBugView {

  companion object {
    fun getInstance(activity: FragmentActivity,
                    repositoryBuilder: BugReportRepository.Builder,
                    screenshotFilePath: String?) =
        ExtrasHandler.getInstance(activity, repositoryBuilder, screenshotFilePath)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.report_bug_activity_layout)

    (findViewById(R.id.toolbar) as? Toolbar)?.let { setSupportActionBar(it) }
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    val presenter = ReportBugPresenter().apply {
      attach(this@ReportBugActivity, ExtrasHandler(intent))
    }

    findViewById(R.id.sendButton).setOnClickListener {
      presenter.onSendButtonClick((findViewById(R.id.titleEditText) as EditText).text.toString(),
          (findViewById(R.id.descriptionEditText) as EditText).text.toString())
    }
  }

  private var progressWeakReference = WeakReference<ProgressDialog>(null)

  override fun showProgressDialog() {
    dismissProgressDialog()

    val dialog = ProgressDialog(this).apply {
      setMessage(getString(R.string.loading))
      isIndeterminate = true
      setCancelable(false)
      show()
    }

    progressWeakReference = WeakReference(dialog)
  }

  override fun dismissProgressDialog() {
    progressWeakReference.get()?.apply { if (isShowing) dismiss() }
    progressWeakReference.clear()
  }

  override fun showSuccessToast() {
    Toast.makeText(this, R.string.success, Toast.LENGTH_LONG).show()
  }

  override fun showErrorDialog(text: String) {
    AlertDialog.Builder(this)
        .setTitle("Error")
        .setPositiveButton(android.R.string.ok, null)
        .setMessage(text)
        .show()
  }

}