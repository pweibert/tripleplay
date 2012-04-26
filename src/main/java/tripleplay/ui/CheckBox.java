//
// Triple Play - utilities for use in PlayN-based games
// Copyright (c) 2011, Three Rings Design, Inc. - All rights reserved.
// http://github.com/threerings/tripleplay/blob/master/LICENSE

package tripleplay.ui;

import react.Slot;
import react.Value;

import playn.core.Image;

/**
 * Displays a checkbox which can be toggled. The checkbox must be configured with either a
 * font-based checkmark, or a checkmark icon, which will be shown when it is checked.
 */
public class CheckBox extends TogglableTextWidget<CheckBox>
{
    /** The checked status of this widget. */
    public final Value<Boolean> checked = Value.create(false);

    /** Creates a checkbox using the default check glyph: U+2713. */
    public CheckBox () {
        this('\u2713');
    }

    /** Creates a checkbox with the supplied check character. */
    public CheckBox (char checkChar) {
        this(checkChar, null);
    }

    public CheckBox (Image checkIcon) {
        this((char)0, checkIcon);
    }

    protected CheckBox (char checkChar, Image checkIcon) {
        _checkStr = String.valueOf(checkChar);
        _checkIcon = checkIcon;
        checked.connect(new Slot<Boolean> () {
            @Override public void onEmit (Boolean checked) {
                set(Flag.SELECTED, checked);
                updateCheckViz();
            }
        });
    }

    @Override protected String text () {
        return (_checkIcon == null) ? _checkStr : null;
    }

    @Override protected Image icon () {
        return _checkIcon;
    }

    @Override protected void onClick () {
        checked.update(isSet(Flag.SELECTED));
    }

    @Override protected void layout () {
        super.layout();
        updateCheckViz();
    }

    protected void updateCheckViz () {
        boolean checked = isSelected();
        if (_tglyph.layer() != null) _tglyph.layer().setVisible(checked);
        if (_ilayer != null) _ilayer.setVisible(checked);
    }

    protected final String _checkStr;
    protected final Image _checkIcon;
}
