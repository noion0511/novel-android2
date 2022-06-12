package com.team3.showbee.ui.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.team3.showbee.R
import com.team3.showbee.databinding.DialogLogOutBinding
import com.team3.showbee.databinding.DialogUserLeaveBinding

class UserLeaveDialog : DialogFragment() {
    private var _binding: DialogUserLeaveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogUserLeaveBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnLeaveOk.setOnClickListener {
            buttonClickListener.onLeaveOkClicked()
            dismiss()
        }
        binding.btnLeaveCancel.setOnClickListener {
//            buttonClickListener.onLogOutCancelClicked()
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnButtonClickListener {
        fun onLeaveOkClicked()
//        fun onLogOutCancelClicked()
    }

    fun setButtonClickListener(buttonClickListener: OnButtonClickListener) {
        this.buttonClickListener = buttonClickListener
    }

    private lateinit var buttonClickListener: OnButtonClickListener
}