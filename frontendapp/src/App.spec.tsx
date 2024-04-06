import { render, screen } from '@testing-library/react'
import App from './App'

describe('test', () => {
  it('react message is present', () => {
    render(<App />)
    const res = screen.getByText(/Hello/i)
    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    // @ts-expect-error
    expect(res).toBeInTheDocument
  })
})
