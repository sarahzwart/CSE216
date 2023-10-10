import * as React from 'react'

/**
 * We want everything to be strongly typed, so let's start by declaring a type
 * for the properties that our Hello component will use.
 */
type AddProps = undefined;


/**
 * Declare our component as a class, so that TypeScript can make sure the
 * properties are type-checked.
 */
export class Add extends React.Component<AddProps> {
    /** This is how we declare default values for the properties */
    static defaultProps = { message: "World" };

    /**
     * The render function will return a JSX element.  In the jsx, we use the
     * "{}" syntax to read fields of the Hello class, using "one-way" binding.
     */
    render() { return  (<div>
                            <h3>Add a New Entry</h3>
                            <label>Title</label>
                            <input type="text" id="newTitle" />
                            <label>Message</label>
                            <textarea id="newMessage"></textarea>
                            <button id="addButton">Add</button>
                            <button id="addCancel">Cancel</button>
                        </div>); }
}