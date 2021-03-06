/*

This file is part of Roboject

Copyright (c) 2010-2011 akquinet A.G.

Contact:  http://www.akquinet.de/en

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.akquinet.de/en.

*/
package de.akquinet.android.roboject.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.app.Activity;


/**
 * <p>
 * Inject a view to the annotated field. For this to work, you must either use
 * {@link InjectLayout} on your activity or call
 * {@link Activity#setContentView(int)} during onCreate..() *before* calling
 * super.onCreate(..).
 *
 * <p>
 * You can specify the id of the view to inject as annotation value. If not
 * supplied, R.id.X will be used, where X is the name of the annotated field.
 *
 * @author Philipp Kumar
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface InjectView {
    String DEFAULT_VALUE = "";

    /**
     * Defines the id of the view to inject. If not supplied,
     * R.id.X will be used, where X is the name of the annotated field.
     */
    String value() default DEFAULT_VALUE;
}
