import * as React from 'react'

/**
 * We want everything to be strongly typed, so let's start by declaring a type
 * for the properties that our Hello component will use.
 */
type HomeProps = {
    /** The only property we will have is a message */
    message: string;
}

/**
 * Declare our component as a class, so that TypeScript can make sure the
 * properties are type-checked.
 */
export class Home extends React.Component<HomeProps> {
    /** This is how we declare default values for the properties */
    static defaultProps = { message: "Home" };

    /**
     * The render function will return a JSX element.  In the jsx, we use the
     * "{}" syntax to read fields of the Hello class, using "one-way" binding.
     */
    render() { return (
                    <div>
                        <h1>Welcome to the {this.props.message}page</h1>
                        <h4>This is the webpage</h4>
                        <p>Hows it hanging there bucko</p>
                        <p>Welcome to Margarittaville</p>
                    </div>
            ); 
    }
}