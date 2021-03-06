/*

This file is part of Roboject

Copyright (c) 2010-2011 akquinet A.G.

Contact:  http://www.akquinet.de/en

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.akquinet.de/en.

 */
package de.akquinet.android.robojecttest.activities;

import android.os.Bundle;
import de.akquinet.android.roboject.RobojectActivity;
import de.akquinet.android.roboject.annotations.InjectObject;
import de.akquinet.android.robojecttest.R;
import de.akquinet.android.robojecttest.activities.InjectObjectTestActivityA.NonSerializableObject;


public class InjectObjectTestActivityB extends RobojectActivity
{
    @InjectObject
    public NonSerializableObject objectExtra;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.viewinject);
        super.onCreate(savedInstanceState);
    }
}
