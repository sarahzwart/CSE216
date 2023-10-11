import * as React from 'react'

/**
 * We want everything to be strongly typed, so let's start by declaring a type
 * for the properties that our Hello component will use.
 */
type EditProps = undefined;


/**
 * Declare our component as a class, so that TypeScript can make sure the
 * properties are type-checked.
 */
export class Edit extends React.Component<EditProps> {
    /** This is how we declare default values for the properties */
    static defaultProps = { message: "World" };

    /**
     * The render function will return a JSX element.  In the jsx, we use the
     * "{}" syntax to read fields of the Hello class, using "one-way" binding.
     */
    render() { return  (<div id="editElement">
                            <h3>Edit an Entry</h3>
                            <label>Title</label>
                            <input type="text" id="editTitle" />
                            <label>Message</label>
                            <textarea id="editMessage"></textarea>
                            <span id="editCreated"></span>
                            <input type="hidden" id="editId" />
                            <button id="editButton">Update</button>
                            <button id="editCancel">Cancel</button>
                        </div>); }
}