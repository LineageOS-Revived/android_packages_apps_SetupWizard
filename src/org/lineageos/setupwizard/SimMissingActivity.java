/*
 * Copyright (C) 2016 The CyanogenMod Project
 *               2017-2022 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.setupwizard;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.setupcompat.util.ResultCodes;

import org.lineageos.setupwizard.util.PhoneMonitor;
import org.lineageos.setupwizard.util.SetupWizardUtils;

public class SimMissingActivity extends BaseSetupWizardActivity {

    public static final String TAG = SimMissingActivity.class.getSimpleName();

    private PhoneMonitor mPhoneMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhoneMonitor = PhoneMonitor.getInstance();
        if (!mPhoneMonitor.simMissing()) {
            finishAction(RESULT_OK);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SetupWizardUtils.enableComponent(this, ChooseDataSimActivity.class);
        SetupWizardUtils.enableComponent(this, MobileDataActivity.class);
    }

    @Override
    public void onNavigateNext() {
        if (mPhoneMonitor.simMissing()) {
            SetupWizardUtils.disableComponent(this, ChooseDataSimActivity.class);
            SetupWizardUtils.disableComponent(this, MobileDataActivity.class);
        } else if (!mPhoneMonitor.isMultiSimDevice() || mPhoneMonitor.singleSimInserted()) {
            SetupWizardUtils.disableComponent(this, ChooseDataSimActivity.class);
        }
        super.onNavigateNext();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.sim_missing_page;
    }

    @Override
    protected int getTitleResId() {
        return R.string.setup_sim_missing;
    }

    @Override
    protected int getIconResId() {
        return R.drawable.ic_sim;
    }

}
