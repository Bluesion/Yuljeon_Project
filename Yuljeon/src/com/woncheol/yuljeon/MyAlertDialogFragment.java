package com.woncheol.yuljeon;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
 
public class MyAlertDialogFragment extends DialogFragment {

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("�˷����� ����")
                .setMessage("�� ���� (�ް�)\n�� ����\n�� �޹�\n�� ����\n�� ���\n�� ��\n�� ����\n�� ��\n�� ����\n�� �������\n�� ������\n�� �丶��\n�� ��Ȳ�꿰")
                .setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
       .create();
    }
}