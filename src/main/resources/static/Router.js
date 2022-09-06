const Router = ({routes}) => {
    let comp;

    Object.keys(routes).forEach(v => {
        if (v === window.location.pathname) {
            comp = routes[v];
        }
    });

    if (comp) {
        return comp;
    } else {
        return (
            <h1>Not Found Page</h1>
        )
    }
}

export default Router;